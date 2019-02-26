package com.example.mifans.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mifans.Adapter.RecyclerAdapter;
import com.example.mifans.Data.News;
import com.example.mifans.Data.ViewType;
import com.example.mifans.Http.HttpCallBackListener;
import com.example.mifans.Http.Httputil;
import com.example.mifans.Json.JsonData;
import com.example.mifans.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class PagerFragment extends Fragment {

    private String url;//接口获取json格式数据
    private View view;
    private List<News> newsList = new ArrayList<News>();
    private List<ViewType> viewTypeList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter newsAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    //通过setArguments方法为fragment传递参数
    public static PagerFragment newInstance(String url) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Url", url);
        pagerFragment.setArguments(bundle);
        return pagerFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_layout, container, false);
        newsList = new ArrayList<>();
        initData();
        recyclerView = view.findViewById(R.id.recycle_view);

        //下拉刷新
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //保存之前的新闻列表
                initData();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();


            }
        });

        newsAdapter = new RecyclerAdapter(newsList, getContext(), viewTypeList);
        recyclerView.setAdapter(newsAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        return view;
    }

    public void initData() {

        Bundle args = getArguments();
        if (args != null) {
            url = args.getString("Url");
        }
        Httputil.sendHttpRequest(url, new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {
                parseJSONByGson(response);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    //使用Gson解析json数据
    private void parseJSONByGson(String respone) {
        Gson gson = new Gson();
        JsonData dataGson = gson.fromJson(respone, JsonData.class);
        List<JsonData.DataBean> storys = dataGson.getData();
        for (JsonData.DataBean storiesBean : storys) {
            if (storiesBean.getImage_list().size() != 0) {

                News news = new News(storiesBean.getTitle(), storiesBean.getImage_list().get(0).getUrl(),
                        storiesBean.getImage_list().get(1).getUrl(), storiesBean.getImage_list().get(2).getUrl(),
                        storiesBean.getMedia_name(), storiesBean.getComment_count() + "", storiesBean.getDatetime(),
                        storiesBean.getUrl(), storiesBean.getGroup_id(), storiesBean.getItem_id(), storiesBean.getMedia_info().getAvatar_url(),
                        storiesBean.getMedia_info().getName(), storiesBean.getSource_open_url().substring(22));
                ViewType viewType = new ViewType(storiesBean.getImage_list().size());
                newsList.add(0, news);
                viewTypeList.add(0, viewType);
            } else {

                News news = new News(storiesBean.getTitle(), storiesBean.getImage_url(),
                        storiesBean.getImage_url(), storiesBean.getImage_url(),
                        storiesBean.getMedia_name(), storiesBean.getComment_count() + "", storiesBean.getDatetime(),
                        storiesBean.getUrl(), storiesBean.getGroup_id(), storiesBean.getItem_id(), storiesBean.getMedia_info().getAvatar_url(),
                        storiesBean.getMedia_info().getName(), storiesBean.getSource_open_url().substring(22));
                ViewType viewType = new ViewType(storiesBean.getImage_list().size());
                newsList.add(0, news);
                viewTypeList.add(0, viewType);
            }


        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsAdapter.notifyDataSetChanged();
            }
        });
    }
}

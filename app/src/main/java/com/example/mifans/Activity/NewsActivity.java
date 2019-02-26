package com.example.mifans.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mifans.Adapter.CommentAdapter;
import com.example.mifans.Data.Comment;
import com.example.mifans.Json.CommentData;
import com.example.mifans.Http.HttpCallBackListener;
import com.example.mifans.Http.Httputil;
import com.example.mifans.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

//新闻详情页
public class NewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private boolean isStar = false;//是否关注
    private List<Comment> comments = new ArrayList<>();
    RecyclerView commentRecyclerView;
    CommentAdapter commentAdapter;
    Button star;
    ImageButton backToNews;
    ImageView writterHead;
    TextView writterName;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        WebView webView = findViewById(R.id.web_view);
        star = findViewById(R.id.star_tv);
        backToNews = findViewById(R.id.back_to_news);
        writterName = findViewById(R.id.writter_name);
        writterHead = findViewById(R.id.writter_head);
        commentRecyclerView = findViewById(R.id.recycle_view_news);


        backToNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            //关注与取消关注
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("star", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                isStar = preferences.getBoolean("star?", true);
                if (isStar) {
                    star.setText("已关注");
                    star.setTextColor(getResources().getColor(R.color.starButtonSelected));
                    star.setBackgroundResource(R.drawable.star_selected);
                    editor.putBoolean("star?", false);
                    editor.apply();
                } else {
                    star.setText("关注");
                    star.setTextColor(getResources().getColor(R.color.starButtonNotSelected));
                    star.setBackgroundResource(R.drawable.star_button);
                    editor.putBoolean("star?", true);
                    editor.apply();
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String url = intent.getStringExtra("article_url");
        final String groupID = intent.getStringExtra("group_id");
        final String itemID = intent.getStringExtra("item_id");
        String avatarUrl = intent.getStringExtra("avatarUrl");
        String mwritterName = intent.getStringExtra("writterName");
        final String mediaID = intent.getStringExtra("media_id");
        String pigknum = intent.getStringExtra("pik_num");

        writterHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, articleActivity.class);
                intent.putExtra("media_Id", mediaID);
                startActivity(intent);
            }
        });
        writterName.setText(mwritterName);
        Glide.with(this).load(avatarUrl).placeholder(R.drawable.loading).into(writterHead);

        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(url);
        //设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "APP_CACHE_DIRNAME";
        // 设置数据库缓存路径
        webView.getSettings().setAppCachePath(cacheDirPath);
        webView.getSettings().setAppCacheEnabled(true);
        initComment(groupID, itemID);
        commentAdapter = new CommentAdapter(this, comments);
        commentRecyclerView.setAdapter(commentAdapter);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        commentRecyclerView.setLayoutManager(manager);


    }


    public void initComment(String groupID, String itemId) {
        Httputil.sendHttpRequest("https://www.toutiao.com/api/comment/list/?group_id=" + groupID + "&item_id=" + itemId + "&offset=0&count=100", new HttpCallBackListener() {
            @Override
            public void onFinish(final String response) {
                parseJSONByGsonComment(response);

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    public void parseJSONByGsonComment(String response) {
        Gson gson = new Gson();
        CommentData commentData = gson.fromJson(response, CommentData.class);
        CommentData.DataBean dataBean = commentData.getData();
        List<CommentData.DataBean.CommentsBean> commentsBeans = dataBean.getComments();
        for (CommentData.DataBean.CommentsBean comment : commentsBeans) {
            Comment comment1 = new Comment(comment.getUser().getAvatar_url(), comment.getUser().getName(),
                    comment.getDigg_count() + "", comment.getText(), comment.getReply_count() + "回复",
                    comment.getCreate_time() + "");
            comments.add(comment1);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                commentAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public void onRefresh() {

    }
}

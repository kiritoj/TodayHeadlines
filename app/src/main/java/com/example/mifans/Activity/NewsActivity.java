package com.example.mifans.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
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
    private boolean ispick = false;
    private boolean iscollect = false;
    private List<Comment> comments = new ArrayList<>();
    RecyclerView commentRecyclerView;
    CommentAdapter commentAdapter;
    Button star;
    ImageButton backToNews;
    ImageView writterHead;
    TextView writterName;
    View writeComment;
    ImageView newsCollect;
    ImageView newsPick;

    private MyDatabaseHelper databaseHelper = new MyDatabaseHelper(NewsActivity.this, "User.db", null, 1);
    SQLiteDatabase database;
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
        newsCollect = findViewById(R.id.news_collect);
        newsPick = findViewById(R.id.news_pick);
        writeComment = findViewById(R.id.write_comment);


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
        newsPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("star", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                ispick = preferences.getBoolean("pick?", true);
                if (ispick) {

                    newsPick.setImageResource(R.mipmap.pick);
                    editor.putBoolean("pick?", false);
                    editor.apply();
                } else {

                    newsPick.setImageResource(R.mipmap.not_pick);
                    editor.putBoolean("pick?", true);
                    editor.apply();
                }
            }
        });

        //写评论

        writeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(NewsActivity.this,R.layout.dialog_name,null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(NewsActivity.this);
                builder.setView(view);
                final EditText editText = view.findViewById(R.id.edit_name);
                TextView textView = view.findViewById(R.id.dialog_title);
                Button buttonOk = view.findViewById(R.id.ok);
                Button buttonCan = view.findViewById(R.id.cancle);
                textView.setText("留下你的神评论吧");
                final AlertDialog dialog = builder.show();
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Comment mycomment = new Comment("https://p3.pstatp.com/thumb/65840005cc82277d58f2", "隔壁老王", "5"
                                , editText.getText().toString(), "9回复", (System.currentTimeMillis() / 1000) + "");
                        comments.add(0, mycomment);
                        commentAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                buttonCan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        final String url = intent.getStringExtra("article_url");
        final String groupID = intent.getStringExtra("group_id");
        final String itemID = intent.getStringExtra("item_id");
        final String avatarUrl = intent.getStringExtra("avatarUrl");
        final String mwritterName = intent.getStringExtra("writterName");
        final String mediaID = intent.getStringExtra("media_id");
        String pigknum = intent.getStringExtra("pik_num");
        //标题信息
        final String title = intent.getStringExtra("title");
        final String commentNum = intent.getStringExtra("commentNum");
        final String sendDate = intent.getStringExtra("sentDate");
        final String imageUrl_1 = intent.getStringExtra("imageUrl_1");
        final String imageUrl_2 = intent.getStringExtra("imageUrl_2");
        final String imageUrl_3 = intent.getStringExtra("imageUrl_3");
        final int type = intent.getIntExtra("type",0);

        newsCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("star", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                iscollect = preferences.getBoolean("collect?", true);
                if (iscollect) {
                    newsCollect.setImageResource(R.drawable.collect_huang);
                    //如果收藏就将信息保存进collect table
                    database = databaseHelper.getWritableDatabase();
                    ContentValues  values = new ContentValues();
                    values.put("title",title);
                    values.put("headImage",avatarUrl);
                    values.put("writterName",mwritterName);
                    values.put("commentNum",commentNum);
                    values.put("sentdate",sendDate);
                    values.put("listImage1",imageUrl_1);
                    values.put("listImage2",imageUrl_2);
                    values.put("listImage3",imageUrl_3);
                    values.put("articalUrl",url);
                    values.put("groupId",groupID);
                    values.put("type",type);
                    values.put("itemId",itemID);
                    values.put("media_id",mediaID);
                    database.insert("Collect",null,values);
                    editor.putBoolean("collect?", false);
                    editor.apply();
                } else {

                    newsCollect.setImageResource(R.drawable.collect_hui);
                    //取消收藏将该条信息从数据库删除
                    database = databaseHelper.getWritableDatabase();
                    //根据标题删除数据
                    database.delete("Collect","title = ?",new String[]{title});
                    editor.putBoolean("collect?", true);
                    editor.apply();
                }
            }
        });
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

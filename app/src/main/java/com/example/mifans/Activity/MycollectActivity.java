package com.example.mifans.Activity;
import com.example.mifans.Adapter.RecyclerAdapter;
import com.example.mifans.Data.News;
import com.example.mifans.Data.ViewType;
import com.example.mifans.R;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MycollectActivity extends AppCompatActivity {
    ImageView imageView;
    private List<News> newsList = new ArrayList<News>();
    private List<ViewType> viewTypeList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        imageView = findViewById(R.id.mycollect_back);
        recyclerView = findViewById(R.id.mycollect_recycleview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initCillectedNews();
        newsAdapter = new RecyclerAdapter(newsList, MycollectActivity.this, viewTypeList);
        recyclerView.setAdapter(newsAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(MycollectActivity.this);
        recyclerView.setLayoutManager(manager);

    }
    public void initCillectedNews(){
        //从数据库加载已经收藏的新闻
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(MycollectActivity.this, "User.db", null, 1);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query("Collect",null,null,null,null,null,null);
        if (cursor.moveToLast()){
            //从后往前遍历，最后收藏的处于最顶层
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String headImage = cursor.getString(cursor.getColumnIndex("headImage"));
                String writterName = cursor.getString(cursor.getColumnIndex("writterName"));
                String commentNum = cursor.getString(cursor.getColumnIndex("commentNum"));
                String sentdate = cursor.getString(cursor.getColumnIndex("sentdate"));
                String listImage1 = cursor.getString(cursor.getColumnIndex("listImage1"));
                String listImage2 = cursor.getString(cursor.getColumnIndex("listImage2"));
                String listImage3 = cursor.getString(cursor.getColumnIndex("listImage3"));
                String articalUrl = cursor.getString(cursor.getColumnIndex("articalUrl"));
                String groupId = cursor.getString(cursor.getColumnIndex("groupId"));
                String itemId = cursor.getString(cursor.getColumnIndex("itemId"));
                String mediaId = cursor.getString(cursor.getColumnIndex("media_id"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                News news = new News(title,listImage1,listImage2,listImage3,writterName,commentNum,sentdate,articalUrl,groupId,itemId,headImage,writterName,mediaId);
                ViewType viewType = new ViewType(type);
                newsList.add(news);
                viewTypeList.add(viewType);
            }while (cursor.moveToPrevious());
        }
    }
}

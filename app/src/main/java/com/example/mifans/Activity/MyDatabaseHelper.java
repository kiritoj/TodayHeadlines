package com.example.mifans.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREAT_USER = "create table User ("
            +"id integer primary key autoincrement,"
            +"nickname text,"
            +"slogan text,"
            +"image blob)";
    private Context context;
    private  static final String CREAT_COLLECT_LIST = "create table Collect ("
            + "id integer primary key autoincrement,"
            +"title text,"
            +"headImage text,"
            +"writterName text,"
            +"commentNum text,"
            +"sentdate text,"
            +"listImage1 text,"
            +"listImage2 text,"
            +"listImage3 text,"
            +"articalUrl text,"
            +"groupId text,"
            +"type int,"
            +"media_id text,"

            +"itemId text)";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_USER);
        db.execSQL(CREAT_COLLECT_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

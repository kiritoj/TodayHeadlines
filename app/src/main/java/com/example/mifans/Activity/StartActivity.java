package com.example.mifans.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.mifans.R;

public class StartActivity extends AppCompatActivity {
    private static final int GOGUIDE = 0;//跳转到第一次打开app的引导页面
    private static final int GOHONME = 1;//跳转到主活动
    private static final int DELAY = 3000;//延迟时间
    private boolean isFirst = false;//默认不是第一次打开app
    private String key = "isFirst";//用于判断是否第一次打开app

    //创建hander对象，处理接收的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOGUIDE:
                    goGuide();
                    break;
                case GOHONME:
                    goHome();
                default:
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//这两句卸载setContentView之前
        setContentView(R.layout.activity_start);
        init();


    }

    public void init() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        isFirst = preferences.getBoolean(key, true);
        if (!isFirst) {
            handler.sendEmptyMessageDelayed(GOHONME, DELAY);//不是第一次打开，跳转至主页面
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, false);
            editor.apply();
            handler.sendEmptyMessageDelayed(GOGUIDE, DELAY);
        }


    }


    public void goHome() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goGuide() {
        Intent intent = new Intent(StartActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }


}

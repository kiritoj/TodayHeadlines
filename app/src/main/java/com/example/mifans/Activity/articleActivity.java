package com.example.mifans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mifans.R;


public class articleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        WebView webView = findViewById(R.id.web_view_article);
        Intent intent = getIntent();
        String mediaID = intent.getStringExtra("media_Id");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("https://www.toutiao.com/c/user/" + mediaID + "/");


    }
}

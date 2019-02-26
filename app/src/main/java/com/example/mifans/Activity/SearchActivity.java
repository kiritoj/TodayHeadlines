package com.example.mifans.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mifans.R;

public class SearchActivity extends AppCompatActivity {
    Button button;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton back = findViewById(R.id.back_bt);
        button = findViewById(R.id.search_but);
        result = findViewById(R.id.result_tv);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("这只是个没什么卵用的搜索框，啊哈哈");
            }
        });


    }
}

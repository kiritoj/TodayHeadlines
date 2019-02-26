package com.example.mifans.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mifans.Adapter.GuideViewPagerAdapter;
import com.example.mifans.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    //存储引导页的3张图片
    private int[] imageView = {R.drawable.guide_picture1, R.drawable.guide_picture2, R.drawable.guide_picture3};
    //存储底部三个圆点
    private int[] dotArray = {R.id.dot1, R.id.dot2, R.id.dot3};
    //底部圆点数组
    private ImageView[] dots = new ImageView[3];
    //引导页图片数组
    private ImageView[] imageViews = new ImageView[3];

    //立即体验按钮
    private Button startUse;
    //适配器
    private ViewPager guideViewPager;
    private List<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide);
        startUse = findViewById(R.id.start_use);
        guideViewPager = findViewById(R.id.pager);
        initDots();
        initViewList();
        startUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //初始化viewlist
    public void initViewList() {
        //初始化3张引导页图片
        for (int i = 0; i < 3; i++) {
            imageViews[i] = new ImageView(this);
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews[i].setImageResource(imageView[i]);
            viewList.add(imageViews[i]);
        }
        guideViewPager.setAdapter(new GuideViewPagerAdapter(viewList));
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            //放前选中页面与底部圆点位置重合时该圆点亮起
            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < dots.length; j++) {
                    if (i == j) {
                        dots[j].setImageResource(R.drawable.dot_light);//亮起
                    } else {
                        dots[j].setImageResource(R.drawable.dot_dark);//暗淡
                    }
                }
                //最后一页显示立即体验按钮
                if (i == dots.length - 1) {
                    startUse.setVisibility(View.VISIBLE);
                } else {
                    startUse.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    //初始化圆点
    public void initDots() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = findViewById(dotArray[i]);
        }
    }

}

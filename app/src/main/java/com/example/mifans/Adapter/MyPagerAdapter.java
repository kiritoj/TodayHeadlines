package com.example.mifans.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private final String[] titles = {"热点", "游戏", "汽车", "故事", "科技", "养生","美食"};

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //给viewpager的每个界面设置title
        return titles[position];
    }
}

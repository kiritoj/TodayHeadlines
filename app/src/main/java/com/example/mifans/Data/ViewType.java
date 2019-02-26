package com.example.mifans.Data;
//用于判断recycleView加载哪种文章布局
public class ViewType {
    private int type;

    public ViewType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

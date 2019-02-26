package com.example.mifans.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mifans.Data.News;
import com.example.mifans.Activity.NewsActivity;
import com.example.mifans.R;
import com.example.mifans.Data.ViewType;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int oneImag = 0;
    private final int threeImag = 3;
    private List<News> datas = new ArrayList<News>();
    private List<ViewType> viewTypes = new ArrayList<ViewType>();
    private Context mContext;

    public RecyclerAdapter(List<News> datas, Context context, List<ViewType> viewTypes) {
        this.datas = datas;
        mContext = context;
        this.viewTypes = viewTypes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //第一种news布局，只展示一张图片（api只返回了1张图片）
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.test, viewGroup, false);
            final RecViewHolder holder = new RecViewHolder(view);
            //给cardView注册点击事件，跳转到新闻详情页
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    News news = datas.get(position);
                    Intent intent = new Intent(mContext, NewsActivity.class);
                    intent.putExtra("article_url", news.getArticleUrl());//向新闻详情页传输文章地址
                    intent.putExtra("group_id", news.getGroupId());
                    intent.putExtra("item_id", news.getItemId());
                    intent.putExtra("avatarUrl", news.getAvatarUrl());
                    intent.putExtra("writterName", news.getWritterName());
                    intent.putExtra("media_id", news.getMediaID());
                    intent.putExtra("pik_num",news.getComments());
                    mContext.startActivity(intent);
                }
            });
            return holder;
        } else {
            //第二种布局，展示3张图片
            view = LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false);
            final ThressImagViewHolder holder = new ThressImagViewHolder(view);
            holder.articleCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    News news = datas.get(position);
                    Intent intent = new Intent(mContext, NewsActivity.class);
                    intent.putExtra("article_url", news.getArticleUrl());//向新闻详情页传输文章地址
                    intent.putExtra("group_id", news.getGroupId());
                    intent.putExtra("item_id", news.getItemId());
                    intent.putExtra("avatarUrl", news.getAvatarUrl());
                    intent.putExtra("writterName", news.getWritterName());
                    intent.putExtra("media_id", news.getMediaID());
                    intent.putExtra("pik_num",news.getComments());
                    mContext.startActivity(intent);
                }
            });
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RecViewHolder) {
            RecViewHolder holder = (RecViewHolder) viewHolder;
            holder.title.setText(datas.get(i).getTitle());
            holder.anthor.setText(datas.get(i).getAuthor());
            holder.commemts.setText(datas.get(i).getComments()+"评论");
            holder.mdate.setText(datas.get(i).getTime());
            Glide.with(mContext).load(datas.get(i).getImagUrl1()).placeholder(R.drawable.loading).error(R.drawable.jzerror).into(holder.imageView);
        } else if (viewHolder instanceof ThressImagViewHolder) {
            ThressImagViewHolder holder = (ThressImagViewHolder) viewHolder;
            holder.articleTitle.setText(datas.get(i).getTitle());
            holder.articleAthor.setText(datas.get(i).getAuthor());
            holder.articleComment.setText(datas.get(i).getComments()+"评论");
            holder.articleDate.setText(datas.get(i).getTime());
            Glide.with(mContext).load(datas.get(i).getImagUrl1()).placeholder(R.drawable.loading).error(R.drawable.jzerror).into(holder.imageOne);
            Glide.with(mContext).load(datas.get(i).getImagUrl2()).placeholder(R.drawable.loading).error(R.drawable.jzerror).into(holder.imageTwo);
            Glide.with(mContext).load(datas.get(i).getImagUrl3()).placeholder(R.drawable.loading).error(R.drawable.jzerror).into(holder.imageThree);


        }

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    //返回需加载哪个布局的标记
    public int getItemViewType(int position) {
        ViewType viewType = viewTypes.get(position);
        if (viewType.getType() == 0) {
            return oneImag;
        } else {
            return threeImag;
        }
    }

    //第一种布局
    class RecViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView anthor;
        TextView commemts;
        TextView mdate;
        CardView cardView;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_iv);
            title = itemView.findViewById(R.id.title);
            anthor = itemView.findViewById(R.id.anthor_tv);
            commemts = itemView.findViewById(R.id.comment_tv);
            mdate = itemView.findViewById(R.id.date_tv);
            cardView = itemView.findViewById(R.id.card_view_1);
        }
    }

    //第二种布局
    class ThressImagViewHolder extends RecyclerView.ViewHolder {
        TextView articleTitle;
        ImageView imageOne;
        ImageView imageTwo;
        ImageView imageThree;
        TextView articleAthor;
        TextView articleComment;
        TextView articleDate;
        CardView articleCardView;


        public ThressImagViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.new_title);
            articleAthor = itemView.findViewById(R.id.author_name);
            articleComment = itemView.findViewById(R.id.comment_num);
            articleDate = itemView.findViewById(R.id.send_time);
            imageOne = itemView.findViewById(R.id.picture1);
            imageTwo = itemView.findViewById(R.id.picture2);
            imageThree = itemView.findViewById(R.id.picture3);
            articleCardView = itemView.findViewById(R.id.card_view_2);


        }
    }

}

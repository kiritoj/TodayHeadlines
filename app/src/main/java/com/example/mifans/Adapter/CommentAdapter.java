package com.example.mifans.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mifans.Data.Comment;
import com.example.mifans.R;
import com.example.mifans.Java.timeStamp;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<Comment> commentList = new ArrayList<Comment>();
    private boolean pick = false;//判断是否点过赞，默认没有点赞

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            //实现点赞和取消点赞
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Comment comment = commentList.get(position);
                SharedPreferences preferences = context.getSharedPreferences("pick", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                pick = preferences.getBoolean("pick?", true);
                if (pick) {
                    holder.imageButton.setBackgroundResource(R.mipmap.pick);
                    holder.mpickNum.setText(String.valueOf(Integer.parseInt(comment.getPickNum()) + 1));
                    editor.putBoolean("pick?", false);
                    editor.apply();

                } else {
                    holder.imageButton.setBackgroundResource(R.mipmap.not_pick);
                    holder.mpickNum.setText(comment.getPickNum());
                    editor.putBoolean("pick?", true);
                    editor.apply();
                }
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.muserName.setText(commentList.get(i).getUserName());
        viewHolder.mpickNum.setText(commentList.get(i).getPickNum());
        viewHolder.mcommentText.setText(commentList.get(i).getCommentText());
        //将时间戳转换为时间，java是精确到毫秒，所以乘以1000
        viewHolder.mcommentTime.setText(timeStamp.stampToDate(commentList.get(i).getCommemtTime() + "000"));
        viewHolder.mreplyNum.setText(commentList.get(i).getReplyNum());
        Glide.with(context).load(commentList.get(i).getHeadUrl()).placeholder(R.drawable.loading).into(viewHolder.mHead);

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mHead;
        TextView muserName;
        TextView mpickNum;
        TextView mcommentText;
        TextView mcommentTime;
        TextView mreplyNum;
        ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHead = itemView.findViewById(R.id.tx_iv);
            muserName = itemView.findViewById(R.id.user_name_tv);
            mpickNum = itemView.findViewById(R.id.pick_num_tv);
            mcommentText = itemView.findViewById(R.id.comment_text_tv);
            mcommentTime = itemView.findViewById(R.id.comment_time_tv);
            mreplyNum = itemView.findViewById(R.id.reply_num_tv);
            imageButton = itemView.findViewById(R.id.pick_ib);
        }
    }
}

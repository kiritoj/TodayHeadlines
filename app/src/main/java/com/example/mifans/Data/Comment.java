package com.example.mifans.Data;

public class Comment {
    private String headUrl;//用户头像
    private String userName;//用户昵称
    private String pickNum;//点赞数
    private String commentText;//评论
    private String replyNum;//回复数量
    private String commemtTime;//回复时间

    public Comment(String headUrl, String userName, String pickNum, String commentText, String replyNum, String commemtTime) {
        this.headUrl = headUrl;
        this.userName = userName;
        this.pickNum = pickNum;
        this.commentText = commentText;
        this.replyNum = replyNum;
        this.commemtTime = commemtTime;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPickNum() {
        return pickNum;
    }

    public void setPickNum(String pickNum) {
        this.pickNum = pickNum;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public String getCommemtTime() {
        return commemtTime;
    }

    public void setCommemtTime(String commemtTime) {
        this.commemtTime = commemtTime;
    }
}

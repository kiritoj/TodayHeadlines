package com.example.mifans.Data;

//新闻实体类
public class News {
    private String title;//文章标题
    private String imagUrl1;//文章图片
    private String imagUrl2;
    private String imagUrl3;
    private String author;//作者
    private String comments;//评论数量
    private String time;//发布时间
    private String articleUrl;//新闻地址
    //用于获取文章评论
    private String groupId;
    private String itemId;

    //获取文章作者信息
    private String avatarUrl;//作者头像地址
    private String writterName;
    private String mediaID;//作者Id


    public News(String title, String imagUrl1, String imagUrl2, String imagUrl3,
                String author, String comments, String time, String articleUrl,
                String groupId, String itemId, String avatarUrl, String writterName, String mediaID) {

        this.title = title;
        this.imagUrl1 = imagUrl1;
        this.imagUrl2 = imagUrl2;
        this.imagUrl3 = imagUrl3;
        this.author = author;
        this.comments = comments;
        this.time = time;
        this.articleUrl = articleUrl;
        this.groupId = groupId;
        this.itemId = itemId;
        this.avatarUrl = avatarUrl;
        this.writterName = writterName;
        this.mediaID = mediaID;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagUrl1() {
        return imagUrl1;
    }

    public void setImagUrl1(String imagUrl1) {
        this.imagUrl1 = imagUrl1;
    }

    public String getImagUrl2() {
        return imagUrl2;
    }

    public void setImagUrl2(String imagUrl2) {
        this.imagUrl2 = imagUrl2;
    }

    public String getImagUrl3() {
        return imagUrl3;
    }

    public void setImagUrl3(String imagUrl3) {
        this.imagUrl3 = imagUrl3;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWritterName() {
        return writterName;
    }

    public void setWritterName(String writterName) {
        this.writterName = writterName;
    }

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }
}

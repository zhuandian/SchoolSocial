package com.zhuandian.schoolsocial.entity;

import cn.bmob.v3.BmobObject;

/**
 * desc :帖子实体
 * author：xiedong
 * date：2019/4/22
 */
public class PostEntity extends BmobObject {

    private String username;
    private String content;
    private UserEntity author;       //发布动态的作者，一对一的思想
    private int commentCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}

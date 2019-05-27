package com.zhuandian.schoolsocial.entity;

import com.zhuandian.schoolsocial.business.chat.bean.User;

import cn.bmob.v3.BmobObject;

/**
 * desc :失物招领实体类
 * author：xiedong
 */
public class LostAndFoundEntity extends BmobObject {
    public static int LOST = 1; //丢失
    public static int FOUND = 2;//捡到
    private String title;
    private String content;
    private User userEntity;
    private String showName;
    private int type;
    //TODO 联系方式，暂不定义

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

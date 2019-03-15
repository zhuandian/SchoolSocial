package com.zhuandian.schoolsocial.entity;

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
    private UserEntity userEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
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

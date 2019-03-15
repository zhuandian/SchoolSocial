package com.zhuandian.schoolsocial.entity;

import cn.bmob.v3.BmobObject;

/**
 * desc :学生诉求
 * author：xiedong
 */
public class FeedBackEntity extends BmobObject {
    private String userName;
    private String userMajor;
    private String userNumber;
    private String userFeedBack;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserFeedBack() {
        return userFeedBack;
    }

    public void setUserFeedBack(String userFeedBack) {
        this.userFeedBack = userFeedBack;
    }
}

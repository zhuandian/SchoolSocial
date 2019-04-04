package com.zhuandian.schoolsocial.entity;

/**
 * desc :
 * author：xiedong
 * date：2019/3/14
 */
public class PersonalScoreEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    private String scores;

}

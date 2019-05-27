package com.zhuandian.schoolsocial.business.chat.bean;


import com.zhuandian.schoolsocial.business.chat.db.NewFriend;

import cn.bmob.v3.BmobUser;

/**
 * @author :smile
 * @project:User
 * @date :2016-01-22-18:11
 */
public class User extends BmobUser {

    private String avatar;
    private String nickName;
    private String userInfo;
    private String nikeName;
    private int roleId;  //1.老师，2学生  根据角色不同在首页推荐不同的内容。

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public User(){}

    public User(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

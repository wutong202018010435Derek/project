package com.lynn.base.beans;

import android.text.TextUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.lynn.base.uitls.ZeroUtil;

/**
 * Created by ABC

 * note
 */
@DatabaseTable(tableName = "userlist")
public class UserBean {


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String userId;
    @DatabaseField
    private String loginName;
    @DatabaseField
    private String password;
    @DatabaseField
    private String userName;

    @DatabaseField
    private String email;
    @DatabaseField
    private String remark;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String userType;

    private String status;

//    public String getId() {
//        return id + "";
//    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return ZeroUtil.subZeroAndDot(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }







}

package com.daclink.drew.sp22.cst438_project01_starter;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private String mUsername;
    private String mPassword;
    private String mEmail;
    private Boolean mIsAdmin;

    public User(String mUsername, String mPassword, String mEmail, Boolean mIsAdmin) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
        this.mIsAdmin = mIsAdmin;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getEmail() { return mEmail; }

    public void setEmail(String mEmail) { this.mEmail = mEmail; }

    public Boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(Boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }
}

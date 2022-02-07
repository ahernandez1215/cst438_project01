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
    private Boolean mIsAdmin;

    public User(String mUsername, String mPassword, Boolean mIsAdmin) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mIsAdmin = mIsAdmin;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public Boolean getmIsAdmin() {
        return mIsAdmin;
    }

    public void setmIsAdmin(Boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }
}

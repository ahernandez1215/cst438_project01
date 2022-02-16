package com.daclink.drew.sp22.cst438_project01_starter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    void insert(User user) {
        mRepository.addUser(user);
    }

    User getUserByUsername(String username) {
        return mRepository.getUserByUsername(username);
    }

    User getUserByUserId(int userId) {
        return mRepository.getUserById(userId);
    }

}

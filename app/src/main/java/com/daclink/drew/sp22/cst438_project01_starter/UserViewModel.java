package com.daclink.drew.sp22.cst438_project01_starter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

import java.util.List;

/**
 *  ViewModel for use in UI fragments or activities relating to user
 */

public class UserViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<User>> mUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mUsers = mRepository.mUsers;
    }

    void insert(User user) {
        mRepository.insertUser(user);
    }

    void update(User user) { mRepository.updateUser(user); }

    void delete(User user) { mRepository.deleteUser(user); }

    User getUserByUsername(String username) {
        return mRepository.getUserByUsername(username);
    }

    User getUserByUserId(int userId) {
        return mRepository.getUserById(userId);
    }

}

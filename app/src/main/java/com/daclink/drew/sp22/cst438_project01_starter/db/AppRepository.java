package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.User;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *  Determines from where to fetch data.
 *  Pull from local room database or web API
 */

public class AppRepository {
    public static AppRepository instance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
    }

    public static AppRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    public void addUser(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDAO().insert(user);
            }
        });
    }

    public void deleteUser(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDAO().delete(user); }
        });
    }

    public User getUserById(int userId) {
        final User[] mUser = new User[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mUser[0] = mDb.userDAO().getUserByUserId(userId);
            }
        });
        return mUser[0];
    }

    public User getUserByUsername(String username) {
        final User[] mUser = new User[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mUser[0] = mDb.userDAO().getUserByUsername(username);
            }
        });
        return mUser[0];
    }
}

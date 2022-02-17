package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  Determines from where to fetch data.
 *  Pull from local room database or web API
 */

public class AppRepository {
    public static AppRepository instance;
    private AppDatabase mDb;
    private UserDAO mUserDao;
    private RecipeAppDAO mRecipeDao;

    public LiveData<List<User>> mUsers;

    public static AppRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    //This constructor is called when AppRepository instance is made.
    public AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mUserDao = mDb.getUserDAO();
        mRecipeDao = mDb.getRecipeAppDAO();
        mUsers = getAllUsers();
    }

    public void addUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.insert(user);
        });

    }

    public void updateUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.update(user);
            mUserDao = mDb.getUserDAO();
        });
    }

    public void deleteUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.delete(user);
        });
    }

    //Callable/Future used to get return value from runnable
    public User getUserById(int userId) {
        User user = new User("1","8","1",false);
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mUserDao.getUserByUserId(userId);
            }
        });

        try {
            user = userFuture.get();
        } catch (Exception e) {

        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = new User("1","8","1",false);
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mUserDao.getUserByUsername(username);
            }
        });

        try {
            user = userFuture.get();
        } catch (Exception e) {
        }
        return user;
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserDao.getAllUsers();
    }
}

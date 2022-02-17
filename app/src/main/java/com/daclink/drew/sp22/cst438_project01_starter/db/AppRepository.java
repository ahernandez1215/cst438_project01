package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

/**
 *  Determines from where to retrieve or update data
 *  (pulls from local room database or food web API)
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

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> mUserDao.insert(user));

    }

    public void updateUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.update(user);
            mUserDao = mDb.getUserDAO();
        });
    }

    public void deleteUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> mUserDao.delete(user));
    }

    //Callable/Future used to get return value from runnable
    public User getUserById(int userId) {
        User user = null;
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(() -> mUserDao.getUserByUserId(userId));

        try {
            user = userFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(() -> mUserDao.getUserByUsername(username));

        try {
            user = userFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserDao.getAllUsers();
    }

    //Recipe methods
    public void insertRecipe(Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> mRecipeDao.insert(recipe));
    }

    public void updateRecipe(Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mRecipeDao.update(recipe);
            mRecipeDao = mDb.getRecipeAppDAO();
        });
    }

    public void deleteRecipe(Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> mRecipeDao.delete(recipe));
    }

    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;
        Future<Recipe> recipeFuture = AppDatabase.databaseWriteExecutor.submit(() -> mRecipeDao.getRecipeByRecipeId(recipeId));
        try {
            recipe = recipeFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public Recipe getRecipeByName(String recipeName) {
        Recipe recipe = null;
        Future<Recipe> recipeFuture = AppDatabase.databaseWriteExecutor.submit(() -> mRecipeDao.getRecipeByName(recipeName));
        try {
            recipe = recipeFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public Recipe getRecipeByApiId(int recipeApiId) {
        Recipe recipe = null;
        Future<Recipe> recipeFuture = AppDatabase.databaseWriteExecutor.submit(() -> mRecipeDao.getRecipeByApiId(recipeApiId));
        try {
            recipe = recipeFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}

package com.daclink.drew.sp22.cst438_project01_starter.db;


import android.speech.RecognitionListener;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.drew.sp22.cst438_project01_starter.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.User;

import java.util.List;

@Dao
public interface RecipeAppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe... recipes);

    @Update
    void update(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);

    @Query("SELECT * FROM " + AppDatabase.RECIPE_TABLE + " ORDER BY mName DESC")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM " + AppDatabase.RECIPE_TABLE + " WHERE mRecipeId = :recipeId")
    Recipe getRecipeByRecipeId(int recipeId);

    @Query("SELECT * FROM " + AppDatabase.RECIPE_TABLE + " WHERE mApiId = :apiId")
    Recipe getRecipeByApiId(int apiId);

    @Query("SELECT * FROM " + AppDatabase.RECIPE_TABLE + " WHERE mName = :name")
    Recipe getRecipeByName(String name);
}

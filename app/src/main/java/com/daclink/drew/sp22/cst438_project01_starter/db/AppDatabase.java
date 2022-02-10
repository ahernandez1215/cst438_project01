package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.drew.sp22.cst438_project01_starter.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.typeConverters.DataTypeConverters;

@Database(entities = {Recipe.class, User.class}, version = 1)
@TypeConverters(DataTypeConverters.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "RECIPE_APP_DATABASE";
    public static final String RECIPE_TABLE = "RECIPE_TABLE";
    public static final String USER_TABLE = "USER_TABLE";

    //public abstract RecipeAppDAO getRecipeAppDAO();

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();
    public abstract RecipeAppDAO recipeAppDAO();

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).build();
        }
        return instance;
    }
}


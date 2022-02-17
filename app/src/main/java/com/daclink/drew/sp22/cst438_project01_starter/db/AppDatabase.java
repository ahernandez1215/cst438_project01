package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.typeConverters.DataTypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class, User.class}, version = 2, exportSchema = false)
@TypeConverters(DataTypeConverters.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "RECIPE_APP_DATABASE";
    public static final String RECIPE_TABLE = "RECIPE_TABLE";
    public static final String USER_TABLE = "USER_TABLE";

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    //Setting number of threads higher causes bugs?
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract RecipeAppDAO getRecipeAppDAO();
    public abstract UserDAO getUserDAO();

    public static AppDatabase getInstance(final Context context) {
        if(instance == null) {
            synchronized (LOCK) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}


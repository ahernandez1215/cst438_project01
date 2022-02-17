package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.RecipeAppDAO;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecipeDAO_Test {

    @Test
    public void recipeInsertTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeAppDAO testDB = AppDatabase.getInstance(appContext).getRecipeAppDAO();

        //Recipe testRecipe = new Recipe()
    }
}

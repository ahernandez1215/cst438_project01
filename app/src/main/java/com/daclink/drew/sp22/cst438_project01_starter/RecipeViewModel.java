package com.daclink.drew.sp22.cst438_project01_starter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.Recipe;

/**
 *  ViewModel for use in UI fragments or activities relating to recipe
 */

public class RecipeViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    void insert(Recipe recipe) {
        mRepository.insertRecipe(recipe);
    }

    void update(Recipe recipe) {
        mRepository.updateRecipe(recipe);
    }

    void delete(Recipe recipe) {
        mRepository.deleteRecipe(recipe);
    }

    Recipe getRecipeById(int recipeId) {
        return mRepository.getRecipeById(recipeId);
    }

    Recipe getRecipeByName(String recipeName) {
        return mRepository.getRecipeByName(recipeName);
    }

    Recipe getRecipeByApiId(int recipeApiId) {
        return mRepository.getRecipeByApiId(recipeApiId);
    }
}

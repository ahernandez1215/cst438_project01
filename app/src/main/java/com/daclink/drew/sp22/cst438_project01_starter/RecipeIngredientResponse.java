package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeIngredientResponse {
    @SerializedName("meals")
    @Expose
    private List<RecipeCategoryInfo> meals = null;

    @NonNull
    public RecipeCategoryInfo getRecipeModel() {
        return meals.get(0);
    }
}

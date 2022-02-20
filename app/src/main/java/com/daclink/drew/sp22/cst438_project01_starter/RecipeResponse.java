package com.daclink.drew.sp22.cst438_project01_starter;

import com.daclink.drew.sp22.cst438_project01_starter.RecipeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeResponse {
    @SerializedName("meals")
    @Expose
    private List<RecipeModel> meals = null;

    public RecipeModel getRecipeModel() {
        return meals.get(0);
    }
}

package com.daclink.drew.sp22.cst438_project01_starter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndpointInterface {

    @GET("api/json/v1/1/lookup.php?i={mealId}")
    Call<RecipeModel> getRecipeById(@Path("mealId") String mealId);

    @GET("api/json/v1/1/filter.php?i={mainIngredient}")
    Call<RecipeModel> getRecipeByMainIngredient(@Path("mainIngredient") String mainIngredient);

    @GET("/api/json/v1/1/search.php?s={mealName}")
    Call<RecipeModel> getRecipeByName(@Path("mealName") String mealName);
}

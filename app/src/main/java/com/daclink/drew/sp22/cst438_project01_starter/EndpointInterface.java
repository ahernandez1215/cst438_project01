package com.daclink.drew.sp22.cst438_project01_starter;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface EndpointInterface {

    @GET("api/json/v1/1/lookup.php")
    Call<RecipeModel> getRecipeById(@Query("i") String mealId);

    @GET("api/json/v1/1/filter.php?i={mainIngredient}")
    Call<RecipeModel> getRecipeByMainIngredient(@Path("mainIngredient") String mainIngredient);

    @GET("/api/json/v1/1/search.php")
    Call<RecipeResponse> getRecipeByName(@Query("s") String mealName);

    @GET("/api/json/v1/1/filter.php")
    Call<RecipeCategoryResponse> getRecipeByCategory(@Query("c") String category);
}

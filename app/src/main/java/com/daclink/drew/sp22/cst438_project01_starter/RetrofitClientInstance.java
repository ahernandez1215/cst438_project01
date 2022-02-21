package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private EndpointInterface endpointInterface;
    private MutableLiveData<RecipeResponse> recipeResponseLiveData;
    private MutableLiveData<RecipeCategoryResponse> recipeCategoryResponseLiveData;
    private MutableLiveData<RecipeIngredientResponse> recipeIngredientResponseLiveData;

    public RetrofitClientInstance(String BASE_URL) {
        recipeResponseLiveData = new MutableLiveData<>();
        recipeCategoryResponseLiveData = new MutableLiveData<>();
        recipeIngredientResponseLiveData = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient();

        endpointInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EndpointInterface.class);
    }

    public void searchByName(String mealName) {
        endpointInterface.getRecipeByName(mealName)
                .enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                        System.out.println("Success!");
                        if(response.body() != null) {
                            recipeResponseLiveData.postValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {
                        System.out.println("Failed!");
                        recipeResponseLiveData.postValue(null);
                        System.out.println("Error" + t.getMessage());
                    }
                });
    }

    public void searchByMainIngredient(String mainIngredient) {
        endpointInterface.getRecipeByMainIngredient(mainIngredient)
                .enqueue(new Callback<RecipeIngredientResponse>() {
                    @Override
                    public void onResponse(Call<RecipeIngredientResponse> call, Response<RecipeIngredientResponse> response) {
                        if(response.body() != null) {
                            recipeIngredientResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeIngredientResponse> call, Throwable t) {
                        System.out.println(call);
                        recipeIngredientResponseLiveData.postValue(null);
                        System.out.println("Error on Main Ingredient call: " + t.getMessage());
                    }
                });
    }

    public void searchByCategory(String mealCategory) {
        endpointInterface.getRecipeByCategory(mealCategory)
                .enqueue(new Callback<RecipeCategoryResponse>() {
                    @Override
                    public void onResponse(Call<RecipeCategoryResponse> call, Response<RecipeCategoryResponse> response) {
                        System.out.println("Success!");
                        if(response.body() != null) {
                            recipeCategoryResponseLiveData.postValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<RecipeCategoryResponse> call, Throwable t) {
                        System.out.println("Failed!");
                        recipeResponseLiveData.postValue(null);
                        System.out.println("Error" + t.getMessage());
                    }
                });

    }

    public LiveData<RecipeResponse> getRecipeResponseLiveData() {
        return recipeResponseLiveData;
    }

    public LiveData<RecipeCategoryResponse> getRecipeCategoryResponseLiveData() {
        return recipeCategoryResponseLiveData;
    }

    public LiveData<RecipeIngredientResponse> getRecipeIngredientResponseLiveData() {
        return recipeIngredientResponseLiveData;
    }
}

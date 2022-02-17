package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    public static final String BASE_URL = "https://www.themealdb.com/";
    private EndpointInterface endpointInterface;
    private MutableLiveData<RecipeModel> recipeModelLiveData;

    public RetrofitClientInstance(String BASE_URL) {
        recipeModelLiveData = new MutableLiveData<>();

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
                .enqueue(new Callback<RecipeModel>() {
                    @Override
                    public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                        
                        if (response.body() != null) {
                            recipeModelLiveData.postValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<RecipeModel> call, Throwable t) {
                        recipeModelLiveData.postValue(null);

                    }
                });
        }

    public LiveData<RecipeModel> getRecipeModelLiveData() {
        return recipeModelLiveData;
    }
}

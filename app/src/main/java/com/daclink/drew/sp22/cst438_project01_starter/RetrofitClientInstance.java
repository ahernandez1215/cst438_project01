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
    private List<RecipeModel> recipeModelList;

    public RetrofitClientInstance(String BASE_URL) {
        recipeModelList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        endpointInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EndpointInterface.class);
    }

    public RecipeModel searchByName(String mealName) {
        endpointInterface.getRecipeByName(mealName)
              .enqueue(new Callback<List<RecipeModel>>() {
                  @Override
                  public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                      System.out.println("Success!");
                      recipeModelList = response.body();
                  }

                  @Override
                  public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                      System.out.println("Failed!");
                      recipeModelList = null;
                  }
              });
            return recipeModelList.get(0);
        }

    public RecipeModel getRecipeModelLiveData() {
        return recipeModelList.get(0);
    }
}

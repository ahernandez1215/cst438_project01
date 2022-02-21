package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class RecipeIngredientActivity extends AppCompatActivity implements LifecycleObserver {

    public static final String BASE_URL = "https://www.themealdb.com/";

    private RetrofitClientInstance retrofitClientInstance = new RetrofitClientInstance(BASE_URL);
    private LiveData<RecipeResponse> recipeModelLiveData;
    private RecipeModel recipeModel;
    private RecipeViewModel recipeViewModel;

    private String mMainIngredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredient);

        mMainIngredient = getIntent().getStringExtra("Main Ingredient");
        System.out.println(mMainIngredient);

        initViewModel();

        getLifecycle().addObserver(this);
        retrofitClientInstance.searchByMainIngredient(mMainIngredient);
        retrofitClientInstance.getRecipeIngredientResponseLiveData().observe(this, new Observer<RecipeIngredientResponse>() {
            @Override
            public void onChanged(RecipeIngredientResponse recipeModelData) {
                try{
                    String recipeName = recipeModelData.getRecipeModel().getStrMeal();
                    Intent intent = DisplayRecipe.intentFactory(getApplicationContext(), recipeName);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Recipe not found!", Toast.LENGTH_SHORT).show();
                    Intent intent = SearchPage.intentFactory(getApplicationContext());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initViewModel() {
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    public static Intent intentFactory(Context context, String mainIngredient) {
        Intent intent = new Intent(context, RecipeIngredientActivity.class);
        intent.putExtra("Main Ingredient", mainIngredient);
        return intent;
    }
}
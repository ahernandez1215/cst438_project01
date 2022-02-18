package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayRecipe extends AppCompatActivity {
    
    private RetrofitClientInstance retrofitClientInstance;
    private LiveData<RecipeModel> recipeModelLiveData;
    private RecipeModel recipeModel;

    private TextView mRecipeName;
    private TextView mInstructions;
    private TextView mIngredient1;
    private TextView mIngredient2;
    private TextView mIngredient3;
    private TextView mIngredient4;
    private TextView mIngredient5;
    private TextView mIngredient6;
    private TextView mIngredient7;
    private TextView mIngredient8;
    private TextView mIngredient9;
    private TextView mIngredient10;
    private TextView mIngredient11;
    private TextView mIngredient12;
    private TextView mIngredient13;
    private TextView mIngredient14;
    private TextView mIngredient15;
    private TextView mIngredient16;
    private TextView mIngredient17;
    private TextView mIngredient18;
    private TextView mIngredient19;
    private TextView mIngredient20;
    private TextView mMeasure1;
    private TextView mMeasure2;
    private TextView mMeasure3;
    private TextView mMeasure4;
    private TextView mMeasure5;
    private TextView mMeasure6;
    private TextView mMeasure7;
    private TextView mMeasure8;
    private TextView mMeasure9;
    private TextView mMeasure10;
    private TextView mMeasure11;
    private TextView mMeasure12;
    private TextView mMeasure13;
    private TextView mMeasure14;
    private TextView mMeasure15;
    private TextView mMeasure16;
    private TextView mMeasure17;
    private TextView mMeasure18;
    private TextView mMeasure19;
    private TextView mMeasure20;



    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        
        wireUpDisplay();
        
        recipeModel = getRecipeByNameApi(getIntent().getStringExtra("Recipe Name"));

        fillInformation(recipeModel);
    }

    private void fillInformation(RecipeModel recipeModel) {
        mRecipeName.setText(recipeModel.getStrMeal());
        mInstructions.setText(recipeModel.getStrInstructions());


    }

    private void wireUpDisplay() {
        mIngredient1 = findViewById(R.id.Ingredient1);
        mIngredient2 = findViewById(R.id.Ingredient2);
        mIngredient3 = findViewById(R.id.Ingredient3);
        mIngredient4 = findViewById(R.id.Ingredient4);
        mIngredient5 = findViewById(R.id.Ingredient5);
        mIngredient6 = findViewById(R.id.Ingredient6);
        mIngredient7 = findViewById(R.id.Ingredient7);
        mIngredient8 = findViewById(R.id.Ingredient8);
        mIngredient9 = findViewById(R.id.Ingredient9);
        mIngredient10 = findViewById(R.id.Ingredient10);
        mIngredient11 = findViewById(R.id.Ingredient11);
        mIngredient12 = findViewById(R.id.Ingredient12);
        mIngredient13 = findViewById(R.id.Ingredient13);
        mIngredient14 = findViewById(R.id.Ingredient14);
        mIngredient15 = findViewById(R.id.Ingredient15);
        mIngredient16 = findViewById(R.id.Ingredient16);
        mIngredient17 = findViewById(R.id.Ingredient17);
        mIngredient18 = findViewById(R.id.Ingredient18);
        mIngredient19 = findViewById(R.id.Ingredient19);
        mIngredient20 = findViewById(R.id.Ingredient20);
        mMeasure1 = findViewById(R.id.measure1);
        mMeasure2 = findViewById(R.id.measure2);
        mMeasure3 = findViewById(R.id.measure3);
        mMeasure4 = findViewById(R.id.measure4);
        mMeasure5 = findViewById(R.id.measure5);
        mMeasure6 = findViewById(R.id.measure6);
        mMeasure7 = findViewById(R.id.measure7);
        mMeasure8 = findViewById(R.id.measure8);
        mMeasure9 = findViewById(R.id.measure9);
        mMeasure10 = findViewById(R.id.measure10);
        mMeasure11 = findViewById(R.id.measure11);
        mMeasure12 = findViewById(R.id.measure12);
        mMeasure13 = findViewById(R.id.measure13);
        mMeasure14 = findViewById(R.id.measure14);
        mMeasure15 = findViewById(R.id.measure15);
        mMeasure16 = findViewById(R.id.measure16);
        mMeasure17 = findViewById(R.id.measure17);
        mMeasure18 = findViewById(R.id.measure18);
        mMeasure19 = findViewById(R.id.measure19);
        mMeasure20 = findViewById(R.id.measure20);
        mInstructions = findViewById(R.id.instructions);
        mRecipeName = findViewById(R.id.RecipeName);
    }

    private RecipeModel getRecipeByNameApi(String recipeName) {
        retrofitClientInstance.searchByName(recipeName);
        recipeModelLiveData = retrofitClientInstance.getRecipeModelLiveData();
        recipeModel = recipeModelLiveData.getValue();
        return recipeModel;
    }

    public static Intent intentFactory(Context context, String recipeName) {
        Intent intent = new Intent(context, DisplayRecipe.class);
        intent.putExtra("Recipe Name", recipeName);
        return intent;
    }
}
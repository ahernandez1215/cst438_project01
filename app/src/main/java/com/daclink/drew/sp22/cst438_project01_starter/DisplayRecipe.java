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

public class DisplayRecipe extends AppCompatActivity implements LifecycleObserver {

    public static final String BASE_URL = "https://www.themealdb.com/";
    
    private RetrofitClientInstance retrofitClientInstance = new RetrofitClientInstance(BASE_URL);
    private LiveData<RecipeResponse> recipeModelLiveData;
    private RecipeModel recipeModel;
    private RecipeViewModel recipeViewModel;

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
    private ImageView mMealThumbView;

    private String recipeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);

        wireUpDisplay();

        recipeName = getIntent().getStringExtra("Recipe Name");
        System.out.println(recipeName);

        initViewModel();

        getLifecycle().addObserver(this);
        retrofitClientInstance.searchByName(recipeName);
        retrofitClientInstance.getRecipeResponseLiveData().observe(this, new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeModelData) {
                try{
                    fillInformation(recipeModelData.getRecipeModel());

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

    private void fillInformation(RecipeModel recipeModel) {
        mRecipeName.setText(recipeModel.getStrMeal());
        mInstructions.setText(recipeModel.getStrInstructions());
        String url = recipeModel.getStrMealThumb();
        Glide.with(mMealThumbView).load(url).into(mMealThumbView);
        mInstructions.setMovementMethod(new ScrollingMovementMethod());
        mIngredient1.setText(recipeModel.getStrIngredient1());
        mIngredient2.setText(recipeModel.getStrIngredient2());
        mIngredient3.setText(recipeModel.getStrIngredient3());
        mIngredient4.setText(recipeModel.getStrIngredient4());
        mIngredient5.setText(recipeModel.getStrIngredient5());
        mIngredient6.setText(recipeModel.getStrIngredient6());
        mIngredient7.setText(recipeModel.getStrIngredient7());
        mIngredient8.setText(recipeModel.getStrIngredient8());
        mIngredient9.setText(recipeModel.getStrIngredient9());
        mIngredient10.setText(recipeModel.getStrIngredient10());
        mIngredient11.setText(recipeModel.getStrIngredient11());
        mIngredient12.setText(recipeModel.getStrIngredient12());
        mIngredient13.setText(recipeModel.getStrIngredient13());
        mIngredient14.setText(recipeModel.getStrIngredient14());
        mIngredient15.setText(recipeModel.getStrIngredient15());
        mIngredient16.setText(recipeModel.getStrIngredient16());
        mIngredient17.setText(recipeModel.getStrIngredient17());
        mIngredient18.setText(recipeModel.getStrIngredient18());
        mIngredient19.setText(recipeModel.getStrIngredient19());
        mIngredient20.setText(recipeModel.getStrIngredient20());
        mMeasure1.setText(recipeModel.getStrMeasure1());
        mMeasure2.setText(recipeModel.getStrMeasure2());
        mMeasure3.setText(recipeModel.getStrMeasure3());
        mMeasure4.setText(recipeModel.getStrMeasure4());
        mMeasure5.setText(recipeModel.getStrMeasure5());
        mMeasure6.setText(recipeModel.getStrMeasure6());
        mMeasure7.setText(recipeModel.getStrMeasure7());
        mMeasure8.setText(recipeModel.getStrMeasure8());
        mMeasure9.setText(recipeModel.getStrMeasure9());
        mMeasure10.setText(recipeModel.getStrMeasure10());
        mMeasure11.setText(recipeModel.getStrMeasure11());
        mMeasure12.setText(recipeModel.getStrMeasure12());
        mMeasure13.setText(recipeModel.getStrMeasure13());
        mMeasure14.setText(recipeModel.getStrMeasure14());
        mMeasure15.setText(recipeModel.getStrMeasure15());
        mMeasure16.setText(recipeModel.getStrMeasure16());
        mMeasure17.setText(recipeModel.getStrMeasure17());
        mMeasure18.setText(recipeModel.getStrMeasure18());
        mMeasure19.setText(recipeModel.getStrMeasure19());
        mMeasure20.setText(recipeModel.getStrMeasure20());


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
        mMealThumbView = findViewById(R.id.recipeImage);
    }

    public static Intent intentFactory(Context context, String recipeName) {
        Intent intent = new Intent(context, DisplayRecipe.class);
        intent.putExtra("Recipe Name", recipeName);
        return intent;
    }
}
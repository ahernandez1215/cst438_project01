package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

public class SearchPage extends AppCompatActivity {

    private Button mSearchByName;
    private Button mSearchByIngredient;
    private Button mSearchByCategory;
    private EditText mRecipeName;
    private EditText mMainIngredient;
    private EditText mRecipeCategory;
    private RecipeModel recipeModel;
    private String recipeNameString;
    private String recipeCategoryString;
    private String mainIngredientString;

    private int mUserId;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        wireUpDisplay();
    }

    private void wireUpDisplay() {

        mSearchByName = findViewById(R.id.buttonSearchByName);
        mSearchByIngredient = findViewById((R.id.buttonSearchByIngredient));
        mRecipeName = findViewById(R.id.editTextSearchByName);
        mMainIngredient = findViewById(R.id.editTextSearchByIngredient);
        mSearchByCategory = findViewById(R.id.searchByCategoryBtn);
        mRecipeCategory = findViewById(R.id.editTextSearchByCategory);

        mSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecipeName != null) {
                    recipeNameString = mRecipeName.getText().toString();
                    System.out.println(recipeNameString);
                    Intent intent = DisplayRecipe.intentFactory(getApplicationContext(), recipeNameString);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a recipe to search for!", Toast.LENGTH_LONG);
                }
            }
        });

        mSearchByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecipeCategory != null) {
                    recipeCategoryString = mRecipeCategory.getText().toString();

                    Intent intent = RecipeCategoryActivity.intentFactory(getApplicationContext(), recipeCategoryString);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a category to search for!", Toast.LENGTH_LONG);
                }
            }
        });

        mSearchByIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMainIngredient != null) {
                    mainIngredientString = mMainIngredient.getText().toString();
                    Intent intent = RecipeIngredientActivity.intentFactory(getApplicationContext(), mainIngredientString);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a main ingredient!", Toast.LENGTH_LONG);
                }
            }
        });
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, SearchPage.class);
        return intent;
    }
}
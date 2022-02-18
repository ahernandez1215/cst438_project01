package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;

public class  SearchPage extends AppCompatActivity {

    private Button mSearchByName;
    private Button mSearchByIngredient;
    private EditText mRecipeName;
    private EditText mMainIngredient;
    private RecipeModel recipeModel;
    private String recipeNameString;
    private RetrofitClientInstance retrofitClientInstance;
    private LiveData<RecipeModel> recipeModelLiveData;

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

        mSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecipeName != null) {
                    recipeNameString = mRecipeName.getText().toString();
                    Intent intent = DisplayRecipe.intentFactory(getApplicationContext(), recipeNameString);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a recipe to search for!", Toast.LENGTH_LONG);
                }
            }
        });
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, SearchPage.class);
        return intent;
    }
}
package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;


public class RecipeCategoryActivity extends AppCompatActivity implements LifecycleObserver {
    public static final String BASE_URL = "https://www.themealdb.com/";

    private RetrofitClientInstance retrofitClientInstance = new RetrofitClientInstance(BASE_URL);

    private TextView mealTextField;
    private ImageView mealThumbView;
    private Button backBtn;

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_cateogry);
        connectDisplay();

        getLifecycle().addObserver(this);
        categoryName = getIntent().getStringExtra("Category Name");
        retrofitClientInstance.searchByCategory(categoryName);
        retrofitClientInstance.getRecipeCategoryResponseLiveData().observe(this, new Observer<RecipeCategoryResponse>() {
            @Override
            public void onChanged(@NonNull RecipeCategoryResponse recipeModelData) {
                try{
                    fillInformation(recipeModelData.getRecipeCategoryInfo());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Category not found!", Toast.LENGTH_SHORT).show();
                    Intent intent = SearchPage.intentFactory(getApplicationContext());
                    startActivity(intent);
                    finish();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchPage.intentFactory(getApplicationContext());
                startActivity(intent);
                finish();
            }
        });
    }

    private void fillInformation(RecipeCategoryInfo recipeCategoryInfo) {
        mealTextField.setText(recipeCategoryInfo.getStrMeal());
        String url = recipeCategoryInfo.getStrMealThumb();
        Glide.with(mealThumbView).load(url).into(mealThumbView);
    }

    private void connectDisplay() {
        mealTextField = findViewById(R.id.textViewMealName);
        mealThumbView = findViewById(R.id.mealImage);
        backBtn = findViewById(R.id.backBtn);
    }

    public static Intent intentFactory(Context context, String recipeName) {
        Intent intent = new Intent(context, RecipeCategoryActivity.class);
        intent.putExtra("Category Name", recipeName);
        return intent;
    }
}

package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private static final String USER_ID_KEY = "com.daclink.drew.sp22.cst438_project01_starter.userIdKey";
    private SharedPreferences mPreferences = null;

    private int mUserId = -1;

    private Button mSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MealDB mealDB;
        Meal meal = new Meal();
//        private void search(){
//            mealDB = MealDBClient.createService(MealDB.class);
//            Call<Meal> call = MealDB.searchMeal(s);
//            call.enqueue(new Callback<Meal>() {
//                @Override
//                public void onResponse(Call<Meal> call, Response<Meal> response) {
//                    meal.addAll(response.body().get)
//                }
//
//                @Override
//                public void onFailure(Call<Meal> call, Throwable t) {
//
//                }
//            });
//        }
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSignInBtn = findViewById(R.id.signInBtn);

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LoginActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        isUserLoggedIn();
    }

    //Method to check if a user is logged in using shared preferences
    private void isUserLoggedIn() {
        //Checks if intent has extra and assigns if it does. If no extra then user is not logged in
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

        if(mUserId != -1) {
            return;
        }
        if(mPreferences == null) {
            getPrefs();
        }

        if(mUserId != -1) {
            //TODO: If user is logged in redirect to landing page
        }
    }

    private void getPrefs() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("USER_ID_KEY", userId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
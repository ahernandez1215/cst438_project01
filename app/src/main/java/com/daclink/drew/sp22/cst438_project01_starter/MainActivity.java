package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.daclink.drew.sp22.cst438_project01_starter.db.UserDAO;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;


import androidx.navigation.ui.AppBarConfiguration;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.daclink.drew.sp22.cst438_project01_starter.userIdKey";
    private SharedPreferences mPreferences = null;

    private int mUserId = -1;

    private Button mSignInBtn;
    private Button mCreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignInBtn = findViewById(R.id.signInBtn);
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LoginActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mCreateAccountBtn = findViewById(R.id.createAccountMainBtn);
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateAccount.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        if(isUserLoggedIn()) {
            Intent intent = LandingPage.intentFactory(this, mUserId);
            startActivity(intent);
            finish();
        }
    }

    private boolean isUserLoggedIn() {
        //Checks if intent has extra and assigns if it does. If no extra then user is not logged in
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

        if(mUserId != -1) {
            return true;
        }
        if(mPreferences == null) {
            getPrefs();
        }

        if(mUserId != -1) {
            return true;
        }
        return false;
    }

    private void getPrefs() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}
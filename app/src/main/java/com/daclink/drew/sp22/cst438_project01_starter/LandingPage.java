package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LandingPage extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.daclink.drew.sp22.cst438_project01_starter.userIdKey";
    private SharedPreferences mPreferences = null;
    private int mUserId = -1;

    private User mUser;
    private AppDatabase mDb;

    private TextView welcomeTextView;
    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        mRepository = AppRepository.getInstance(this.getApplicationContext());


        welcomeTextView = findViewById(R.id.textViewWelcome);
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        System.out.println("THIS IS USER ID: " + mUserId);

        mUser = mRepository.getUserById(mUserId);

        welcomeTextView.setText(String.format("Welcome %s", mUser.getUsername()));
        saveUserPreferences(mUser.getUserId());


    }

    private void saveUserPreferences(int userId) {
        if(mPreferences == null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}
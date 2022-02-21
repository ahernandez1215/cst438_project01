package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDAO;

import java.util.Objects;

public class LandingPage extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.daclink.drew.sp22.cst438_project01_starter.userIdKey";
    private SharedPreferences mPreferences = null;
    private int mUserId = -1;
    private User mUser;
    private UserViewModel mUserViewModel;

    private Button mLogoutBtn;
    private Button mEditAccountBtn;
    private Button mSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        initViewModel();
        connectDisplay();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Homepage");
        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        System.out.println("THIS IS USER ID: " + mUserId);
        mUser = mUserViewModel.getUserByUserId(mUserId);
        welcomeTextView.setText(String.format("Welcome %s", mUser.getUsername()));
        saveUserPreferences(mUser.getUserId());

        mEditAccountBtn.setOnClickListener(view -> {
            Intent intent = EditAccountActivity.intentFactory(getApplicationContext(), mUser.getUserId());
            startActivity(intent);
        });

        mSearchBtn.setOnClickListener(view -> {
            Intent intent = SearchPage.intentFactory(getApplicationContext());
            startActivity(intent);
        });

        mLogoutBtn.setOnClickListener(view -> logout());

    }

    private void connectDisplay() {
        mEditAccountBtn = findViewById(R.id.editAccountBtn);
        mLogoutBtn = findViewById(R.id.logout);
        mSearchBtn = findViewById(R.id.searchBtn);
    }

    private void initViewModel() {
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void saveUserPreferences(int userId) {
        if(mPreferences == null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    private void logout() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage(R.string.logout);
        alertBuilder.setPositiveButton(getString(R.string.no),
                (dialog, which) -> {});
        alertBuilder.setNegativeButton(getString(R.string.yeslogout),
                (dialog, which) -> {
                    getIntent().putExtra(USER_ID_KEY, -1);
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putInt(USER_ID_KEY, -1);
                    editor.apply();
                    Intent intent = MainActivity.intentFactory(getApplicationContext(), -1);
                    startActivity(intent);
                    finish();
                });
        alertBuilder.create().show();
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}
package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDAO;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;

    private String mUsername;
    private String mPassword;

    private Button mLoginButton;
    private User mUser;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectDisplay();
        initViewModel();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputFields();

                if(checkUser()) {
                    if(!validPwd()) {
                        Toast.makeText(LoginActivity.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void initViewModel() {
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void connectDisplay() {
        mUsernameField = findViewById(R.id.username);
        mPasswordField = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.loginBtn);
    }

    private void getInputFields() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    private boolean validPwd() { return mUser.getPassword().equals(mPassword); }

    private boolean checkUser() {
        mUser = mUserViewModel.getUserByUsername(mUsername);

        if(mUser == null) {
            Toast.makeText(this, "User " + mUsername + " not found ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}
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

public class CreateAccount extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    private String mUsername;
    private String mEmail;
    private String mPassword;

    private Button mCreateNewAccountBtn;
    private User mUser;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        connectDisplay();
        initViewModel();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Creating Account");

        mCreateNewAccountBtn.setOnClickListener(view -> {
            getInputFields();

            if(!checkUserExists()) {
                if(mUsername.isEmpty() || mPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cannot have empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                User newUser = new User(mUsername, mPassword, mEmail, false);
                mUserViewModel.insert(newUser);
                mUser = mUserViewModel.getUserByUsername(mUsername);

                Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId());
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViewModel() {
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void connectDisplay() {
        mUsernameField = findViewById(R.id.username);
        mEmailField = findViewById(R.id.emailEditText);
        mPasswordField = findViewById(R.id.password);
        mCreateNewAccountBtn = findViewById(R.id.createNewAccountBtn);
    }

    private void getInputFields() {
        mUsername = mUsernameField.getText().toString();
        mEmail = mEmailField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    private boolean checkUserExists() {
        mUser = mUserViewModel.getUserByUsername(mUsername);

        if(mUser != null) {
            return true;
        }
        return false;
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, CreateAccount.class);
        return intent;
    }
}
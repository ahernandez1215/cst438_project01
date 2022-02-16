package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateAccount extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;

    private String mUsername;
    private String mPassword;

    private Button mCreateNewAccountBtn;
    private User mUser;

    private UserViewModel mUserViewModel;
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        connectDisplay();
        initViewModel();

        mCreateNewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputFields();

                if(!checkUserExists()) {
                    User newUser = new User(mUsername, mPassword, "otter@csumb.edu", false);
                    mUserViewModel.insert(newUser);

                    Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId());
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
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
        mCreateNewAccountBtn = findViewById(R.id.createNewAccountBtn);
    }

    private void getInputFields() {
        mUsername = mUsernameField.getText().toString();
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
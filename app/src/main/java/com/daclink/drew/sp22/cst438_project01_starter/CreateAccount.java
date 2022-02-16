package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateAccount extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;

    private String mUsername;
    private String mPassword;

    private Button mCreateNewAccountBtn;
    private User mUser;

    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        connectDisplay();
        mRepository = AppRepository.getInstance(this);

        mCreateNewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputFields();

                if(!checkUserExists()) {
                    User newUser = new User(mUsername, mPassword, "otter@csumb.edu", false);
                    mRepository.addUser(newUser);
                    mUser = mRepository.getUserByUsername(mUsername);

                    Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId());
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        mUser = mRepository.getUserByUsername(mUsername);

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
package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

public class EditAccountActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.daclink.drew.sp22.cst438_project01_starter.userIdKey";
    private SharedPreferences mPreferences = null;
    private int mUserId = -1;

    private UserViewModel mUserViewModel;

    private EditText editUsernameField;
    private EditText editEmailField;
    private EditText editPasswordField;

    private Button updateAccountBtn;
    private Button deleteAccountBtn;

    private String mUsername;
    private String mEmail;
    private String mPassword;

    private User mUser;
    private User mUpdatedUser;
    private User mCurrentUser;

    private boolean updateAccount = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        connectDisplay();
        initViewModel();
        getCurrentUserId();

        mCurrentUser = mUserViewModel.getUserByUserId(mUserId);

        editUsernameField.setText(mCurrentUser.getUsername());
        editEmailField.setText(mCurrentUser.getEmail());

        updateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputFields();
                mUpdatedUser = new User(mUsername, mPassword, mEmail, false);
                mUser = mUserViewModel.getUserByUsername(mUsername);

                //If null we aren't updating existing user
                if(mUser == null) {
                    updateAccount = true;
                } else if (mUser.getUserId() != mCurrentUser.getUserId()) {
                    Toast.makeText(EditAccountActivity.this, "Cannot edit someone else!", Toast.LENGTH_SHORT).show();
                } else {
                    updateAccount = true;
                }

                if(isEmptyPassword()) {
                    Toast.makeText(EditAccountActivity.this, "Cannot have empty password", Toast.LENGTH_SHORT).show();
                    updateAccount = false;
                }

                if(updateAccount) {
                    mUpdatedUser.setUserId(mCurrentUser.getUserId());
                    mUserViewModel.update(mUpdatedUser);
                    Toast.makeText(EditAccountActivity.this, "Account Updated!", Toast.LENGTH_SHORT).show();
                    mUser = mUserViewModel.getUserByUsername(mUsername);

                    saveUserPreferences(mCurrentUser.getUserId());
                    Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId());
                    startActivity(intent);
                }
            }
        });

        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });
        saveUserPreferences(mUserId);
    }

    private void connectDisplay() {
        editUsernameField = findViewById(R.id.editUsername);
        editEmailField = findViewById(R.id.editEmail);
        editPasswordField = findViewById(R.id.editPassword);

        updateAccountBtn = findViewById(R.id.updateAccountButton);
        deleteAccountBtn = findViewById(R.id.deleteAccountButton);
    }

    //Check for empty password
    private boolean isEmptyPassword() {
        if(mPassword.isEmpty()) {
            return true;
        }
        return false;
    }

    private void getInputFields() {
        mUsername = editUsernameField.getText().toString();
        mEmail = editEmailField.getText().toString();
        mPassword = editPasswordField.getText().toString();
    }

    private void initViewModel() {
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void getCurrentUserId() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
    }

    private void deleteAccount() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage("Delete Account");
        alertBuilder.setPositiveButton(getString(R.string.nodont),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
        alertBuilder.setNegativeButton(getString(R.string.yesdelete),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mUser = mUserViewModel.getUserByUserId(mUserId);
                        mUserViewModel.delete(mUser);
                        logout();
                    }
                });
        alertBuilder.create().show();
    }

    private void logout() {
        getIntent().putExtra(USER_ID_KEY, -1);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, -1);
        editor.apply();
        Intent intent = MainActivity.intentFactory(this.getApplicationContext(), -1);
        startActivity(intent);
        finish();
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
        Intent intent = new Intent(context, EditAccountActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}

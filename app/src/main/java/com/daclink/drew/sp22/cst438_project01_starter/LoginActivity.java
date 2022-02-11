//package com.daclink.drew.sp22.cst438_project01_starter;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
//import com.daclink.drew.sp22.cst438_project01_starter.db.RecipeAppDAO;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private EditText mUsernameField;
//    private EditText mPasswordField;
//
//    private String mUsername;
//    private String mPassword;
//
//    private Button mLoginButton;
//    private RecipeAppDAO mRecipeAppDAO;
//
//    private User mUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        connectDisplay();
//
//        mLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getInputFields();
//
//                if(checkUser()) {
//                    if(!validPwd()) {
//                        Toast.makeText(LoginActivity.this, "Invalid password!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        //TODO: Take to landing page
//                    }
//                }
//            }
//        });
//    }
//
//    private void connectDisplay() {
//        mUsernameField = findViewById(R.id.username);
//        mPasswordField = findViewById(R.id.password);
//        mLoginButton = findViewById(R.id.loginBtn);
//    }
//
//    private void getInputFields() {
//        mUsername = mUsernameField.getText().toString();
//        mPassword = mPasswordField.getText().toString();
//    }
//
//    private boolean validPwd() { return mUser.getmPassword().equals(mPassword); }
//
//    private boolean checkUser() {
//        mUser = mRecipeAppDAO.getUserByUsername(mUsername);
//
//        if(mUser == null) {
//            Toast.makeText(this, "User " + mUsername + " not found ", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }
//
//    public static Intent intentFactory(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        return intent;
//    }
//
//    //May need to change this later. Use LiveData instead!
//    private void getDatabase() {
//        mRecipeAppDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
//                .allowMainThreadQueries()
//                .build()
//                .getRecipeAppDAO();
//    }
//}
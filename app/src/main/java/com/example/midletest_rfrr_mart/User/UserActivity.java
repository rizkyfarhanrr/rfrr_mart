package com.example.midletest_rfrr_mart.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.midletest_rfrr_mart.R;
import com.example.midletest_rfrr_mart.auth.SigninActivity;
import com.example.midletest_rfrr_mart.function.Preferences;

public class UserActivity extends AppCompatActivity {

    TextView loggedin_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        loggedin_user = findViewById(R.id.tv_username_get);


        loggedin_user.setText("Berhasil login sebagai :" + " " + Preferences.getloginuser(getBaseContext()));
    }

    public void Logout_User(View view) {
        Preferences.clearloggedinuser(getBaseContext());
        startActivity(new Intent(UserActivity.this, SigninActivity.class));
        finish();
    }
}
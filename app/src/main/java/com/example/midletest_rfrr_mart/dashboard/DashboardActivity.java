package com.example.midletest_rfrr_mart.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.midletest_rfrr_mart.R;
import com.example.midletest_rfrr_mart.User.UserActivity;
import com.example.midletest_rfrr_mart.auth.SigninActivity;
import com.example.midletest_rfrr_mart.auth.SignupActivity;
import com.example.midletest_rfrr_mart.function.Preferences;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getloginuserstatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), UserActivity.class));
            finish();
        }
    }

    public void GoToWisata(View view) {
    }

    public void GoTODestinasi(View view) {
    }

    public void GoToPenginapan(View view) {
    }

    public void GoToAboutApp(View view) {
    }

    public void GoToSignIn(View view) {
        startActivity(new Intent(DashboardActivity.this, SigninActivity.class));
        finish();
    }

    public void GoToSignUp(View view) {
        startActivity(new Intent(DashboardActivity.this, SignupActivity.class));
        finish();
    }
}

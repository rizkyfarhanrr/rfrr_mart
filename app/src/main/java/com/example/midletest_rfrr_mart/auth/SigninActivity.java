package com.example.midletest_rfrr_mart.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midletest_rfrr_mart.R;
import com.example.midletest_rfrr_mart.User.UserActivity;
import com.example.midletest_rfrr_mart.dashboard.DashboardActivity;
import com.example.midletest_rfrr_mart.function.Preferences;
import com.example.midletest_rfrr_mart.model.ResponseLogin;
import com.example.midletest_rfrr_mart.networking.ApiServices;
import com.example.midletest_rfrr_mart.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    EditText user_signin, passwd_signin , code_signin;
    Button btn_signin_act;

    boolean cancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        user_signin = (EditText)findViewById(R.id.username_signin);
        passwd_signin = (EditText)findViewById(R.id.pass_signin);
        code_signin = (EditText)findViewById(R.id.codeLogin);

        btn_signin_act = (Button)findViewById(R.id.btn_signin);

        if (getIntent().getExtras() != null) {
            Bundle adapterTampung = getIntent().getExtras();
            user_signin.setText(adapterTampung.getString("SENT_USERNAME"));
            passwd_signin.setText(adapterTampung.getString("SENT_PASSWORD"));
            code_signin.setText(adapterTampung.getString("SENT_CODE"));

//            Log.d("SIGNIN", "TAMPUNG : " + user_signin.getText().toString() + ", " + passwd_signin.getText().toString());
        }

        btn_signin_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upsetUsername = user_signin.getText().toString().trim();
                String upsetPasswd = passwd_signin.getText().toString().trim();
                String upsetCode = code_signin.getText().toString().trim();

                modulConfirmInput(upsetUsername, upsetPasswd, upsetCode);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getloginuserstatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), UserActivity.class));
            finish();
        }
    }

    private void modulConfirmInput(String upsetUsername, String upsetPasswd, String upsetCode) {
        if (TextUtils.isEmpty(upsetUsername)) {
            user_signin.setError("isi username anda");
            user_signin.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(upsetPasswd)) {
            passwd_signin.setError("isi password anda");
           passwd_signin.requestFocus();
           cancel = true;
        } else if (TextUtils.isEmpty(upsetCode)) {
            code_signin.setError("isi code anda");
            code_signin.requestFocus();
            cancel = true;
        } else {
            Preferences.setregistereduser(getBaseContext(), upsetUsername);
            Preferences.setregisteredpass(getBaseContext(), upsetPasswd);
            Preferences.setregisteredCode(getBaseContext(), upsetCode);

            modulLoginUser(upsetUsername, upsetPasswd, upsetCode);
        }
    }

    private void modulLoginUser(String upsetUsername, String upsetPasswd, String upsetCode) {
        final ProgressDialog dialog = ProgressDialog.show(SigninActivity.this, "Please wait... ","Signin Proccessing... ");

        ApiServices apiServices = RetrofitClient.getInstanceRetrofit();

        Call<ResponseLogin> responseLoginCall = apiServices.LoginUser(upsetUsername, upsetPasswd , upsetCode);

        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();

                    String message = response.body().getMessage();
                    int messageCode = response.body().getCode();

                    if (messageCode == 200) {
                        Toast.makeText(SigninActivity.this, message, Toast.LENGTH_SHORT).show();
                        String datauserlogin = String.valueOf((response.body().getInformasiUser()));
                        Log.d("LOG", "USERLOGIN" + datauserlogin);

                        String status = "Login successfully.";
                        if (message.equals(status)) {
                            modulSigninPreferences();
                        }
                    }   else {
                        Toast.makeText(SigninActivity.this,  message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(SigninActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void modulSigninPreferences() {
        Preferences.setloginuser(getBaseContext(), Preferences.getregistereduser(getBaseContext()));
        Preferences.setloginuserstatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), UserActivity.class));
        finish();
    }

    public void goToRegister(View view) {
        startActivity(new Intent(SigninActivity.this, SignupActivity.class));
        finish();
    }

    public void BacktoDashboardIntro(View view) {
        startActivity(new Intent(SigninActivity.this, DashboardActivity.class));
        finish();
    }

}

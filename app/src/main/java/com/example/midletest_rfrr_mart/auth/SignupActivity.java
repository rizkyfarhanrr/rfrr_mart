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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.midletest_rfrr_mart.R;
import com.example.midletest_rfrr_mart.function.Preferences;
import com.example.midletest_rfrr_mart.model.ResponseRegister;
import com.example.midletest_rfrr_mart.networking.ApiServices;
import com.example.midletest_rfrr_mart.networking.RetrofitClient;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText ed_namalengkap, ed_jeniskelamin, ed_user,  ed_passwd, ed_confirm_passwd, ed_email_signup , code_user , phone_user;
    Button btn_signupRegist;

    Bundle adapterBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // connect id

        ed_namalengkap = (EditText)findViewById(R.id.nama_lengkap);
        ed_jeniskelamin = (EditText)findViewById(R.id.jenis_kelamin);
        ed_user = (EditText)findViewById(R.id.username_signup);
        ed_passwd = (EditText)findViewById(R.id.pass_signup);
        ed_confirm_passwd = (EditText)findViewById(R.id.conf_pass_signup);
        ed_email_signup = (EditText)findViewById(R.id.email_signup);
        code_user = (EditText)findViewById(R.id.codeSignup);
        phone_user = (EditText)findViewById(R.id.phone_signup);

        btn_signupRegist = (Button)findViewById(R.id.btn_signup_regist);


        //  connect button

        btn_signupRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modulConfirmInput();
            }
        });


    }

    public void modulConfirmInput() {
        String getnamalengkap, getjeniskelamin, getusername, getpassword, getconfirmPASSWD, getemail ,getcode,getphone;
        boolean cancel = false;

        getnamalengkap = ed_namalengkap.getText().toString().trim();
        getjeniskelamin = ed_jeniskelamin.getText().toString().trim();
        getusername = ed_user.getText().toString().trim();
        getpassword = ed_passwd.getText().toString().trim();
        getconfirmPASSWD = ed_confirm_passwd.getText().toString().trim();
        getemail = ed_email_signup.getText().toString().trim();
        getcode = code_user.getText().toString().trim();
        getphone = phone_user.getText().toString().trim();

        if (getnamalengkap.isEmpty()) {
            ed_namalengkap.setError("isi nama lengkap anda!");
            ed_namalengkap.requestFocus();
        } else if (TextUtils.isEmpty(getjeniskelamin)) {
            ed_jeniskelamin.setError("isi jenis kelamin anda!");
            ed_jeniskelamin.requestFocus();
        } else if (TextUtils.isEmpty(getusername)) {
            ed_user.setError("isi username anda!");
            ed_user.requestFocus();
            cancel = true;
        } else if (cekuser(getusername)) {
            ed_user.setError("username sudah terdaftar!");
            ed_user.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(getemail)) {
            ed_email_signup.setError("isi Email anda!");
            ed_email_signup.requestFocus();
        } else if (TextUtils.isEmpty(getpassword)) {
            ed_passwd.setError("isi password anda!");
            ed_passwd.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(getconfirmPASSWD)) {
            ed_confirm_passwd.setError("isi confirm password anda!");
            ed_confirm_passwd.requestFocus();
            cancel = true;
        } else if (!getconfirmPASSWD.equals(getpassword)) {
            ed_confirm_passwd.setError("password tidak sama!");
            ed_confirm_passwd.requestFocus();
            cancel = true;
        } else if (!checkpasswd(getpassword, getconfirmPASSWD)) {
            ed_passwd.setError("isi password anda!");
            ed_passwd.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(getcode)) {
            code_user.setError("isi Code anda!");
            code_user.requestFocus();
        } else if (TextUtils.isEmpty(getphone)) {
            phone_user.setError("isi no HP/Telp anda!");
            phone_user.requestFocus();
        } else {
            // TODO A.1: ADD PREFERENCES HERE
            Preferences.setregistereduser(getBaseContext(), getusername);
            Preferences.setregisteredpass(getBaseContext(), getpassword);

            moduleregisteruser(getnamalengkap, getjeniskelamin, getusername, getemail, getpassword, getcode , getphone);
        }
    }

    public void moduleregisteruser(String nm_user, String jk_user, final String usr_user,String eml_user, final String pwd_user, String cd_user, String phn_user){
        final ProgressDialog dialog = ProgressDialog.show(SignupActivity.this, "Please wait ....", "Register Proccessing..");


        ApiServices apiServices = RetrofitClient.getInstanceRetrofit();
        Call<ResponseRegister> responseRegisterCall = apiServices.registerUser(nm_user,jk_user,usr_user,eml_user,pwd_user,cd_user,phn_user);

            responseRegisterCall.enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if (response.isSuccessful()){
                        dialog.dismiss();
                        
                        String message = response.body().getMessage();
                        Boolean status = response.body().isStatus();
                        
                        if (status.equals(true)) {
                            Toast.makeText(SignupActivity.this, "Register Succesfully !", Toast.LENGTH_SHORT).show();
                            String body = String.valueOf(response.body().getDataUser());

                            Log.d("LOG", "Register " + body);

                            adapterBundle = new Bundle();
                            adapterBundle.putString("SENT_USERNAME", usr_user);
                            adapterBundle.putString("SENT_PASSWORD", pwd_user);
                            adapterBundle.putString("SENT_CODE", cd_user);

                            Intent kirimkeSignin = new Intent(SignupActivity.this, SigninActivity.class);

                            kirimkeSignin.putExtras(adapterBundle);
                            startActivity(kirimkeSignin);
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Ooops!" + message, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

    }

    private boolean checkpasswd (String passkey, String repasskey){
        return passkey.equals(repasskey);
    }

    private boolean cekuser (String user){
        return user.equals(Preferences.getregistereduser(getBaseContext()));
    }

    public void BacktoSignIn(View view) {
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
        finish();
    }
}
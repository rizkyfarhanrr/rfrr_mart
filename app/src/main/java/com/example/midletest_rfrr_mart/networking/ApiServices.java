package com.example.midletest_rfrr_mart.networking;

import com.example.midletest_rfrr_mart.model.ResponseLogin;
import com.example.midletest_rfrr_mart.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseRegister> registerUser(
                    @Field("namalengkap_user") String str_namalkp,
                    @Field("jeniskelamin_user") String str_jnskelammin,
                    @Field("username_user") String str_username,
                    @Field("password_user") String str_passwd,
                    @Field("email_user") String str_emailusr,
                    @Field("kode_user") String str_kodeuser,
                    @Field("phone_user") String str_phoneuser

            );

    @FormUrlEncoded
    @POST("signin.php")
    Call<ResponseLogin> LoginUser(
            @Field("username") String str_username_login,
            @Field("password") String str_passwd_login,
            @Field("kode_user") String str_code_login
            );
}

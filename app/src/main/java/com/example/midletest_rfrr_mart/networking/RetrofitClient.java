package com.example.midletest_rfrr_mart.networking;
import com.example.midletest_rfrr_mart.function.Constraints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

        private static Retrofit getRetrofit() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constraints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }

        public static ApiServices getInstanceRetrofit(){
            return getRetrofit().create(ApiServices.class);
        }
}
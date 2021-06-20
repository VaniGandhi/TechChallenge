package com.techchallenge.network;

import android.text.TextUtils;

import androidx.core.os.BuildCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import com.techchallenge.utils.UserSharedPrefernces;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitClient instance;
    UserSharedPrefernces usersharedPrefernce;

    public RetrofitClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging =  new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;
                String token = UserSharedPrefernces.getInstance().getTOKEN();
                usersharedPrefernce= UserSharedPrefernces.getInstance();


                if (!TextUtils.isEmpty(token)) {
                    request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json").build();
                } else {
                    request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                }

                return chain.proceed(request);
            }
        }).connectTimeout(13, TimeUnit.MINUTES)
                .readTimeout(13, TimeUnit.MINUTES)
                .addInterceptor(logging)

                .writeTimeout(13, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://testa2.aisle.co/V1/")
                .client(httpClient.build())

                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();

        }
        return instance;
    }

    public ApiInterface getapi() {
        return retrofit.create(ApiInterface.class);

    }

}

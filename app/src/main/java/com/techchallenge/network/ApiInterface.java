package com.techchallenge.network;

import com.techchallenge.model.LoginParamModel;
import com.techchallenge.model.LoginResponseModel;
import com.techchallenge.model.OTPResponseModel;
import com.techchallenge.model.ProfileResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("users/phone_number_login")
    Call<LoginResponseModel> login(@Body LoginParamModel model);

    @POST("users/verify_otp")
    Call<OTPResponseModel> verifyNumber(@Body LoginParamModel model);

    @GET("users/test_profile_list")
    Call<ProfileResponseModel> getProfileDetail();



}

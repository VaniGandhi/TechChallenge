package com.techchallenge.interfaces;

import com.techchallenge.model.OTPResponseModel;
import com.techchallenge.model.ProfileResponseModel;

import retrofit2.Response;

public interface ProfileView {

    void onSuccess(Response<ProfileResponseModel> responseModel);
    void onFailure(Throwable throwable);
}

package com.techchallenge.interfaces;

import com.techchallenge.model.LoginResponseModel;
import com.techchallenge.model.OTPResponseModel;

import retrofit2.Response;

public interface LoginView {

     void onSucessLogin(Response<LoginResponseModel> responseModel);
      void onFailure(Throwable throwable);


    void onSucessVerify(Response<OTPResponseModel> responseModel);
    void onFailureVerify(Throwable throwable);
}

package com.techchallenge.presenter;

import com.techchallenge.interfaces.LoginView;
import com.techchallenge.model.LoginParamModel;
import com.techchallenge.model.LoginResponseModel;
import com.techchallenge.model.OTPResponseModel;
import com.techchallenge.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {


    private RetrofitClient retrofitClient;
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
    }


    public void login(LoginParamModel model) {
        Call<LoginResponseModel> call = retrofitClient.getapi().login(model);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                view.onSucessLogin(response);
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void verifyNumber(LoginParamModel model) {
        Call<OTPResponseModel> call = retrofitClient.getapi().verifyNumber(model);
        call.enqueue(new Callback<OTPResponseModel>() {
            @Override
            public void onResponse(Call<OTPResponseModel> call, Response<OTPResponseModel> response) {
                view.onSucessVerify(response);
            }

            @Override
            public void onFailure(Call<OTPResponseModel> call, Throwable t) {
                view.onFailureVerify(t);
            }
        });
    }

}

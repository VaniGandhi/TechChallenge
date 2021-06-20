package com.techchallenge.presenter;

import com.techchallenge.interfaces.LoginView;
import com.techchallenge.interfaces.ProfileView;
import com.techchallenge.model.LoginParamModel;
import com.techchallenge.model.LoginResponseModel;
import com.techchallenge.model.ProfileResponseModel;
import com.techchallenge.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private RetrofitClient retrofitClient;
    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
    }

    public void getProfile() {
        Call<ProfileResponseModel> call = retrofitClient.getapi().getProfileDetail();
        call.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
                view.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ProfileResponseModel> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}

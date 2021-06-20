package com.techchallenge.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.techchallenge.base.BaseApplication;

public class UserSharedPrefernces {

    private String TOKEN="token";
    private String ISLOGIN="islogin";

    SharedPreferences sharedPreferences;
    static UserSharedPrefernces userSharedPrefernce = null;
    Context context;

    public UserSharedPrefernces() {

    }

    public UserSharedPrefernces(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    }

    public static UserSharedPrefernces

    getInstance() {
        if (userSharedPrefernce == null) {
            userSharedPrefernce = new UserSharedPrefernces(BaseApplication.getContext());
        }
        return userSharedPrefernce;
    }


    public void setTOKEN(String token) {
        sharedPreferences.edit().putString(TOKEN,token).apply();
    }

    public String getTOKEN() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public void setISLOGIN(Boolean islogin) {
        sharedPreferences.edit().putBoolean(ISLOGIN,islogin).apply();
    }

    public Boolean getISLOGIN() {
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }




}

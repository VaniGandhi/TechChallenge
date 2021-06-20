package com.techchallenge.base;

import android.app.Application;
import android.content.Context;



public class BaseApplication extends Application {
    static Context context;
    public static final String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance()
    {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        mInstance = this;

    }

    static public Context getContext() {
        return context;
    }




}
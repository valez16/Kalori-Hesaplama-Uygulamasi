package com.kalori.hesapla;

import android.app.Application;

import com.kalori.hesapla.login.LoginManager;
import com.kalori.hesapla.login.LoginManagerStub;

public class FoodApplication extends Application {

    private LoginManager loginManager;

    @Override
    public void onCreate() {
        super.onCreate();
        loginManager = new LoginManagerStub(this);
    }

    public LoginManager getLoginManager() {
        return  loginManager;
    }
}

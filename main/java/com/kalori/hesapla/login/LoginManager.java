package com.kalori.hesapla.login;

import com.kalori.hesapla.DailyMacroCounter.DailyMacroCounterFragment;


public interface LoginManager {

    void setOnLoginListener(OnLoginListener onLoginListener);


    void login(String username, String password, DailyMacroCounterFragment dailyMacroCounterFragment);
    long getUserId();

    void logout();


    void register(String username,String password, String passwordCheck);

    boolean isLoggedIn();

    String getUsername();

    String getUuid();


}

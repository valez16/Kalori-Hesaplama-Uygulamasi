package com.kalori.hesapla.login;

public interface OnLoginListener {
    void onLogin(String uuid);

    void onLogout();

    void onRegister(String uuid);

    void onError(String message);

}

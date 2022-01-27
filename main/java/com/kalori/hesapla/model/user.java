package com.kalori.hesapla.model;

import com.kalori.hesapla.sqlite.Identifiable;

public class user implements Identifiable<Long> {
    private long id;
    private String UUID;
    private String username;
    private String password;
    private boolean is_login;

    public user() {
    }

    public user(String username, String password) {
        this.username = username;
        this.UUID= java.util.UUID.randomUUID().toString();
        this.password = password;
    }
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_login() {
        return is_login;
    }

    public void setIs_login(boolean is_login) {
        this.is_login = is_login;
    }
}

package com.kalori.hesapla.model;

import com.kalori.hesapla.sqlite.Identifiable;

public class User_Daily_Consumption implements Identifiable<Long> {
    private long id;
    private String date;

    public User_Daily_Consumption(long user_id,String date) {
        this.user_id = user_id;
        this.date=date;
    }
    public User_Daily_Consumption() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    private long user_id;




    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}

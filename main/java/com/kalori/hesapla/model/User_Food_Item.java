package com.kalori.hesapla.model;

import com.kalori.hesapla.sqlite.Identifiable;

public class User_Food_Item implements Identifiable<Long> {
    public long getDaily_Id() {
        return daily_Id;
    }

    public void setDaily_Id(long daily_Id) {
        this.daily_Id = daily_Id;
    }

    public long getFood_Id() {
        return food_Id;
    }

    public void setFood_Id(long food_Id) {
        this.food_Id = food_Id;
    }

    public int getNum_Of_Serving() {
        return num_Of_Serving;
    }

    public void setNum_Of_Serving(int num_Of_Serving) {
        this.num_Of_Serving = num_Of_Serving;
    }

    public meal getMeal() {
        return meal;
    }

    public void setMeal(meal meal) {
        this.meal = meal;
    }

    public int getTotal_Calories() {
        return total_Calories;
    }

    public void setTotal_Calories(int total_Calories) {
        this.total_Calories = total_Calories;
    }

    public User_Food_Item() {
    }

    public User_Food_Item(long daily_Id, long food_Id, int num_Of_Serving, meal meal) {
        this.daily_Id = daily_Id;
        this.food_Id = food_Id;
        this.num_Of_Serving = num_Of_Serving;
        this.meal = meal;
        this.total_Calories = total_Calories;
    }
    private long id;
    private long daily_Id;
    private long food_Id;
    private int num_Of_Serving;
    private meal meal;
    private int total_Calories;


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}

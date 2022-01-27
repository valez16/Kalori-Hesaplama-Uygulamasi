package com.kalori.hesapla.WeeklyProgression;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.caloriecounter.R;
import com.kalori.hesapla.model.DatabaseHandler;
import com.kalori.hesapla.model.User_Daily_Consumption;
import com.kalori.hesapla.model.User_Food_Item;
import com.kalori.hesapla.model.food_Item;

import java.util.List;

public class WeeklyProgressionFragment extends Fragment {
    private DatabaseHandler dbh;
    private WeeklyProgressionActivity weeklyProgressionActivity;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long userId;
    private int totalDayCalories;
    private int getTotalWeekCalories;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_weekly_progression, container, false);
        dbh = new DatabaseHandler(getContext());
        weeklyProgressionActivity = (WeeklyProgressionActivity) getActivity();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getWeeklyCalories(String chosenDate){
        totalDayCalories = 0;
        User_Daily_Consumption userDailyConsumption = dbh.get_User_Daily_Consumption(chosenDate, userId);
        if(userDailyConsumption.getDate() == null)
            return 0;
        List<User_Food_Item> userFoodItems_List = dbh.get_User_Food_Items_By_DailyId(userDailyConsumption.getId());

        if(userFoodItems_List != null){
            for (User_Food_Item user_food_item : userFoodItems_List ) {
                food_Item foodItem = dbh.get_FoodItem_By_FoodItemId(user_food_item.getFood_Id());
                totalDayCalories += (foodItem.getCalories() * user_food_item.getNum_Of_Serving());
            }
        }

        getTotalWeekCalories += totalDayCalories;
        return totalDayCalories;
    }

    public int getGetTotalWeekCalories(){
        return getTotalWeekCalories;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
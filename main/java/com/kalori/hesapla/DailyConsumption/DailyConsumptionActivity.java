package com.kalori.hesapla.DailyConsumption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kalori.hesapla.FoodDisplay.FoodDisplayActivity;
import com.kalori.hesapla.WeeklyProgression.WeeklyProgressionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kalori.hesapla.model.food_Item;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.caloriecounter.R;

public class DailyConsumptionActivity extends AppCompatActivity {

    //Kullanıcının günlük tüketimi için kullanılacak tarih
    public String date;
    public long userId;
    public String day;
    private DailyConsumptionFragment dailyConsumptionFragment;

    //Günlük makro besin öğesinden id alır
    public long getUserId() {
        Intent intent = getIntent();
        userId=intent.getLongExtra("userId",1);
        return userId;
    }
    //Günlük makro besin öğesinden tarih alır
    public String getDate(){
        Intent intent = getIntent();
        date=intent.getStringExtra("date");
        return date;
    }
    public String getToday(){
        Intent intent = getIntent();
        day=intent.getStringExtra("day");
        return day;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);
        dailyConsumptionFragment = (DailyConsumptionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        FloatingActionButton addFood_FAB = findViewById(R.id.addFood_FAB);
        FloatingActionButton dailyFoodReturn=findViewById(R.id.dailyFoodReturn_FAB);
        FloatingActionButton weekly_FAB=findViewById(R.id.WeeklyFAB);

        addFood_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity =(Activity) view.getContext();
                Intent intent = new Intent(activity, FoodDisplayActivity.class);
                activity.startActivityForResult(intent,1);
            }
        });
        dailyFoodReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                if(date!=null)
                    intent.putExtra("date",date);
                intent.putExtra("day",day);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        weekly_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity =(Activity) view.getContext();
                Intent intent = new Intent(activity, WeeklyProgressionActivity.class);
                intent.putExtra("userId",dailyConsumptionFragment.getUserId());
                intent.putExtra("day",day);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        food_Item food;
        if(resultCode==Activity.RESULT_OK) {
            String returnType = data.getStringExtra("returnType");
            if (returnType != null) {
                if (returnType.equals("FoodDisplay")) {
                    food = data.getParcelableExtra("foodToAdd");
                    dailyConsumptionFragment.addFoodToMeal(food);

                }
                else if (returnType.equals("WeeklyProgression")) {
                    day=data.getStringExtra("day");
                    dailyConsumptionFragment.setDayOfWeek(data.getStringExtra("day"));
                    date=data.getStringExtra("date");
                    dailyConsumptionFragment.updateDay(data.getStringExtra("date"));
                }
            }
        }
    }
}
package com.kalori.hesapla.WeeklyProgression;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caloriecounter.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyProgressionActivity extends AppCompatActivity {
    private WeeklyProgressionFragment weeklyProgressionFragment;
    // Günlük kalorileri tutar
    private int dayCalories;
    // Seçili olan bugünü tutar
    private String day;
    // Tarihi tutar
    private String localDate;
    private String today;
    //Kullanıcı ID
    private long userId;

    private String mondayDate;
    private String tuesdayDate;
    private String wednesdayDate;
    private String thursdayDate;
    private String fridayDate;
    private String saturdayDate;
    private String sundayDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_progression);

        weeklyProgressionFragment = (WeeklyProgressionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);

        Intent intent = getIntent();
        userId=intent.getLongExtra("userId",5);
        weeklyProgressionFragment.setUserId(userId);
        TextView editMonday = findViewById(R.id.monday_Calories);
        TextView editTuesday = findViewById(R.id.tuesday_Calories);
        TextView editWednesday = findViewById(R.id.wednesday_Calories);
        TextView editThursday = findViewById(R.id.thursday_Calories);
        TextView editFriday = findViewById(R.id.friday_Calories);
        TextView editSaturday = findViewById(R.id.saturday_Calories);
        TextView editSunday = findViewById(R.id.sunday_Calories);
        TextView totalCaloriesText = findViewById(R.id.totalWeekCalories);

        localDate  = LocalDate.now().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        today = simpleDateFormat.format(new Date());

        long todayNum = getNumToday(today);
        setDate(todayNum);

        editMonday.setText("Pazartesi: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(mondayDate)) + " kalori");
        editTuesday.setText("Salı: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(tuesdayDate))  + " kalori");
        editWednesday.setText("Çarşamba: " +Integer.toString(weeklyProgressionFragment.getWeeklyCalories(wednesdayDate))  + " kalori");
        editThursday.setText("Perşembe: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(thursdayDate))  + " kalori");
        editFriday.setText("Cuma: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(fridayDate))  + " kalori");
        editSaturday.setText("Cumartesi: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(saturdayDate))  + " kalori");
        editSunday.setText("Pazar: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(sundayDate))  + " kalori");
        totalCaloriesText.setText("Hafta: " + Integer.toString(weeklyProgressionFragment.getGetTotalWeekCalories()) + " kalori");

        editMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Pazartesi";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", mondayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });



        editTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Salı";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", tuesdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Çarşamba";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", wednesdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Perşembe";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", thursdayDate);


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Cuma";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", fridayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Cumartesi";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", saturdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                day = "Pazar";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", sundayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }

    public long getNumToday(String today){

        long numDay =0;
        switch (today){
            case "Pazartesi":
                numDay=0;
                break;
            case "Salı":
                numDay=1;
                break;
            case "Çarşamba":
                numDay=2;
                break;
            case "Perşembe":
                numDay=3;
                break;
            case "Cuma":
                numDay=4;
                break;
            case "Cumartesi":
                numDay=5;
                break;
            case "Pazar":
                numDay=6;
                break;
        }

        return numDay;

    }


    public void setDate( long numToday){
        LocalDate tmpLocalDate = LocalDate.now();

        long dayOfWeek = Day.MONDAY.ordinal();
        long dayDifference = dayOfWeek - numToday;
        mondayDate = tmpLocalDate.plusDays(dayDifference).toString();;

        dayOfWeek = Day.TUESDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        tuesdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        dayOfWeek = Day.WEDNESDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        wednesdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        dayOfWeek = Day.THURSDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        thursdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        dayOfWeek = Day.FRIDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        fridayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        dayOfWeek = Day.SATURDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        saturdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        dayOfWeek = Day.SUNDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        sundayDate = tmpLocalDate.plusDays(dayDifference ).toString();

    }

    public String getLocalDate(){
        return localDate;
    }

    public String getDay(){
        return day;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}
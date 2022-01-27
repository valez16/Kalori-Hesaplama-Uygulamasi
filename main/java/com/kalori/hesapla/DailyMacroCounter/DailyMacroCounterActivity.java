package com.kalori.hesapla.DailyMacroCounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.kalori.hesapla.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DailyMacroCounterActivity extends AppCompatActivity {

    private String localDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    private String day;
    private long userId;

    private DailyMacroCounterFragment dailyMacroCounterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_macro_counter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dailyMacroCounterFragment = (DailyMacroCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        localDate = LocalDate.now().toString();

        FloatingActionButton editDaily = findViewById(R.id.edit_Daily);



        editDaily.setOnClickListener(new View.OnClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dailyMacroCounterFragment.getContext(), DailyConsumptionActivity.class);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                if(day==null)
                    day = simpleDateFormat.format(new Date());

                intent.putExtra("userId", userId);

                intent.putExtra("day", day);
                intent.putExtra("returntype", "DailyMacroCounter");
                intent.putExtra("date",localDate);


                startActivityForResult(intent, 1);

            }
        });

    }
    public void setIdAndStarterData(long userId){
        this.userId=userId;
        dailyMacroCounterFragment.getDailyMacroNutrients();
        dailyMacroCounterFragment.setMacroNutrientText();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            day=data.getStringExtra("day");
            localDate = data.getStringExtra("date");
            dailyMacroCounterFragment.getDailyMacroNutrients();
            dailyMacroCounterFragment.setMacroNutrientText();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            localDate = LocalDate.now().toString();
            day = null;
            dailyMacroCounterFragment.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    public String getLocalDate(){
        return localDate;
    }

}
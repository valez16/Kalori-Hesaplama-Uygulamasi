package com.kalori.hesapla.DailyConsumption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.caloriecounter.R;
import com.kalori.hesapla.model.DatabaseHandler;
import com.kalori.hesapla.model.User_Daily_Consumption;
import com.kalori.hesapla.model.User_Food_Item;
import com.kalori.hesapla.model.food_Item;
import com.kalori.hesapla.model.meal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailyConsumptionFragment extends Fragment {
    //Görüntüleyiciler
    private View root;

    //Butonlar
    private Button breakfast_Button;
    private Button lunch_Button;
    private Button dinner_Button;
    private Button snacks_Button;

    //Text Görüntüleyecileri
    public TextView dayOfWeek;
    public TextView dailyCalorieIntake;


    //Diğerleri
    private RecyclerView foodRecyclerView;
    private DailyConsumptionAdapter adapter;
    private DailyConsumptionFragment dailyConsumptionFragment;
    private DailyConsumptionActivity dailyConsumptionActivity;
    private int maximumDailyCalories;
    private TextView dailyCaloriePercent;
    public meal Meal;
    private List<food_Item> food_items_list;
    private String date;
    private long userId;

    private DatabaseHandler dbh;
    private User_Daily_Consumption daily_consumption;

    //Alıcılar
    public long getUserId() {return userId;}
    public meal getMeal(){ return this.Meal; }
    public User_Daily_Consumption getDaily_consumption() {  return daily_consumption;}

    //Belirteçler
    public void setDaily_consumption(User_Daily_Consumption daily_consumption) {this.daily_consumption = daily_consumption; }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_daily_consumption, container, false);
        dailyConsumptionFragment=this;
        dailyConsumptionActivity= (DailyConsumptionActivity) getActivity();

        //Metin Görüntüleyicileri belirler
        dayOfWeek=root.findViewById(R.id.day_TextView);
        dayOfWeek.setText(dailyConsumptionActivity.getToday());
        dailyCaloriePercent = root.findViewById(R.id.dailyIntakePercent_TextView);
        dailyCalorieIntake=root.findViewById(R.id.dailyCalories_TextView);


        //Sorgular için kullanıcı ID'sini çeker
        userId=dailyConsumptionActivity.getUserId();
        //İlk başlangıçta seçilen öğün
        Meal=meal.breakfast;

        //Yeni bir DB handler oluşturur
        dbh = new DatabaseHandler(getContext());
        //Aktiviteye gönderilen tarihi çeker
        date=dailyConsumptionActivity.getDate();
        //Find the daily consumption associated with that date
        daily_consumption = dbh.get_User_Daily_Consumption(date,userId);

        //Günlük bir tüketim yoksa yeni bir tane oluşturur
        if(daily_consumption.getDate()==null){
            try{
                daily_consumption=new User_Daily_Consumption(userId,date);
                dbh.getUser_Daily_Consumption_Table().create(daily_consumption);
            }catch (Exception e){

            }
        }


        //Geçerli günün kahvaltısı için yiyecek çeker
        food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);

        //Bağdaştırıcı ve geri dönüştürücü görünümünü ayarlar
        adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
        foodRecyclerView = root.findViewById(R.id.dailyConsumption_RecyclerView);
        foodRecyclerView.setAdapter(adapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Günlük kalorinin ne kadarını harcadığının gösterir
        maximumDailyCalories=2500;
        calculateTotalCalories(food_items_list);

        //Butonlar
        breakfast_Button=root.findViewById(R.id.breakfast_Button);
        lunch_Button=root.findViewById(R.id.lunch_Button);
        dinner_Button=root.findViewById(R.id.dinner_Button);
        //Tıklandığında renk değişimi kahvaltı için
        breakfast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.green));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                Meal=meal.breakfast;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        lunch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tıklandığında renk değişimi öğle yemeği için
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.green));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                Meal=meal.lunch;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        dinner_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tıklandığında renk değişimi akşam yemeği için
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.green));
                Meal=meal.dinner;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        return root;
    }

    //Yemek listesine yemeği ekler
    public void addFoodToMeal(food_Item food){

        adapter.addFoodItem(food);
    }
    //Görüntülenen günü günceller ve tarihi ayarlar
    public void setDayOfWeek(String day){
        date=day;
        dayOfWeek.setText(day);
    }
    //Geçerli günü çeker
    public String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String today = simpleDateFormat.format(new Date());
        return today;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    //Günlük tüketimi günceller, o tarihte yoksa yenisini oluşturur
    public void updateDay(String date){
        try{
            daily_consumption = dbh.get_User_Daily_Consumption(date,userId);
            if(daily_consumption.getDate()==null){
                daily_consumption = new User_Daily_Consumption(userId,date);
                dbh.getUser_Daily_Consumption_Table().create(daily_consumption);
            }
            food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
            adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
            foodRecyclerView.setAdapter(adapter);
        }catch (Exception e){

        }
    }
    //Yediğiniz günlük kalori miktarını maksimum kalori miktarınıza göre hesaplar.
    public void calculateTotalCalories(List<food_Item> mealsOfDayList){
        int total_Calories=0;
        double caloriePercentage;
        long daily_Id=daily_consumption.getId();
        for (food_Item food : mealsOfDayList){
            User_Food_Item userFoodItem=dbh.getSpecificUserFoodItem(daily_Id,food.getId(),Meal);
            total_Calories=food.getCalories()*userFoodItem.getNum_Of_Serving()+total_Calories;
        }

        dailyCalorieIntake.setText(Integer.toString(total_Calories));
        dailyCaloriePercent.setText(Double.toString(Math.round(((double) total_Calories/(double)maximumDailyCalories)*100))+"%");
    }
}
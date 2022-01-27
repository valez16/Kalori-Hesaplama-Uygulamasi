package com.kalori.hesapla.FoodDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.R;
import com.kalori.hesapla.model.Category;
import com.kalori.hesapla.model.MacroNutrient;
import com.kalori.hesapla.model.food_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FoodDisplayActivity extends AppCompatActivity {

    private Activity activity;
    private FoodDisplayFragment foodDisplayFragment;
    private food_Item food;
    private MacroNutrient macro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);
        Toolbar toolbar = findViewById(R.id.toolbar);

        activity=this;

        foodDisplayFragment=(FoodDisplayFragment)getSupportFragmentManager().findFragmentById(R.id.foodDisplay_Fragment);



        FloatingActionButton addFoodListButton = findViewById(R.id.addFoodList_Button);
        addFoodListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                food_Item foodIntent=new food_Item();
                foodIntent.setCategory(Category.Belirtilmemiş);

                Intent intent = new Intent(activity, AddFoodItemActivity.class);
                intent.putExtra("newFood", foodIntent);
                intent.putExtra("newMacro",new MacroNutrient());
                intent.putExtra("method", "add");
                activity.startActivityForResult(intent,1);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_OK){

            food=data.getParcelableExtra("newFood");
            macro=data.getParcelableExtra("newMacro");
            String method=data.getStringExtra("method");
            foodDisplayFragment.setFoodItem(food,macro,method);
        }

    }


}
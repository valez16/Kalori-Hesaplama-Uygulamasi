package com.kalori.hesapla.FoodDisplay;

import android.content.Intent;
import android.os.Bundle;

import com.kalori.hesapla.model.MacroNutrient;
import com.kalori.hesapla.model.food_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;

import com.example.caloriecounter.R;

public class AddFoodItemActivity extends AppCompatActivity {

    private AddFoodItemFragment addFoodItemFragment;
    private Button addFoodSubmitButton;
    private FloatingActionButton testButton;


    private food_Item newFood;
    private MacroNutrient newMacro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);
        Toolbar toolbar = findViewById(R.id.toolbar);

        addFoodItemFragment=(AddFoodItemFragment)getSupportFragmentManager().findFragmentById(R.id.addFoodItem_Fragment);

        Intent intent=getIntent();
        newFood=intent.getParcelableExtra("newFood");
        newMacro=intent.getParcelableExtra("newMacro");
        String method=intent.getStringExtra("method");

        addFoodItemFragment.setNewFood(newFood, method);
        addFoodItemFragment.setNewMacro(newMacro,method);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
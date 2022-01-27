package com.kalori.hesapla.FoodDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.caloriecounter.R;
import com.kalori.hesapla.model.Category;
import com.kalori.hesapla.model.MacroNutrient;
import com.kalori.hesapla.model.food_Item;

public class AddFoodItemFragment extends Fragment {

    private EditText nameEditText;
    private EditText servingEditText;
    private EditText caloriesEditText;
    private EditText proteinEditText;
    private EditText cholesterolEditText;
    private EditText unsaturatedEditText;
    private EditText saturatedEditText;
    private EditText transEditText;
    private EditText fiberEditText;
    private EditText sugarEditText;
    private EditText sodiumEditText;

    private Button addFoodSubmitButton;
    private Button addFoodCancelButton;

    private food_Item newFood;
    private MacroNutrient newMacro;
    private AddFoodItemActivity addFoodItemActivity;

    private Spinner categorySpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_add_food_item, container, false);

        addFoodItemActivity=(AddFoodItemActivity) getActivity();

        nameEditText=root.findViewById(R.id.name_EditText);
        servingEditText=root.findViewById(R.id.serving_EditText);
        caloriesEditText=root.findViewById(R.id.calories_EditText);
        proteinEditText=root.findViewById(R.id.protein_EditText);
        cholesterolEditText=root.findViewById(R.id.cholesterol_EditText);
        unsaturatedEditText=root.findViewById(R.id.unsaturated_EditText);
        saturatedEditText=root.findViewById(R.id.saturated_EditText);
        transEditText=root.findViewById(R.id.transfat_EditText);
        fiberEditText=root.findViewById(R.id.fiber_EditText);
        sugarEditText=root.findViewById(R.id.sugar_EditText);
        sodiumEditText=root.findViewById(R.id.sodium_EditText);


        servingEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        caloriesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        proteinEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        cholesterolEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        unsaturatedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        saturatedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        transEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        fiberEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        sugarEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        sodiumEditText.setInputType(InputType.TYPE_CLASS_NUMBER);




        categorySpinner=root.findViewById(R.id.category_Spinner);
        categorySpinner.setAdapter(new ArrayAdapter<Category>(getContext(), android.R.layout.simple_spinner_item,Category.values()));


        addFoodSubmitButton=root.findViewById(R.id.addFoodSubmit_Button);

        addFoodSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               newFood.setName(nameEditText.getText().toString());
               newFood.setCategory(Category.valueOf(categorySpinner.getSelectedItem().toString()));
               newFood.setServing_Size(Integer.parseInt(servingEditText.getText().toString()));
               newFood.setCalories(Integer.parseInt(caloriesEditText.getText().toString()));

               newMacro.setProtein(Double.parseDouble(proteinEditText.getText().toString()));
               newMacro.setCholesterol(Double.parseDouble(cholesterolEditText.getText().toString()));
               newMacro.setFiber(Double.parseDouble(fiberEditText.getText().toString()));
               newMacro.setSugar(Double.parseDouble(sugarEditText.getText().toString()));
               newMacro.setSodium(Double.parseDouble(sodiumEditText.getText().toString()));
               newMacro.setSaturatedFat(Double.parseDouble(saturatedEditText.getText().toString()));
               newMacro.setUnsaturatedFat(Double.parseDouble(unsaturatedEditText.getText().toString()));
               newMacro.setTrans_fat(Double.parseDouble(transEditText.getText().toString()));

               Intent intent = addFoodItemActivity.getIntent();
               intent.putExtra("newFood",newFood);
               intent.putExtra("newMacro",newMacro);
               addFoodItemActivity.setResult(Activity.RESULT_OK,intent);
               addFoodItemActivity.finish();
            }
        });

        addFoodCancelButton=root.findViewById(R.id.addFoodCancel_Button);
        addFoodCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFoodItemActivity.setResult(Activity.RESULT_CANCELED);
                addFoodItemActivity.finish();
            }
        });


        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public food_Item getNewFood(){
        return newFood;
    }


    public MacroNutrient getMacro(){
        return newMacro;
    }


    public void setNewFood(food_Item newFood,String method) {
        this.newFood = newFood;

        if(method.equals("edit")){
            nameEditText.setText(newFood.getName());
            categorySpinner.setSelection(newFood.getCategory().ordinal());
            servingEditText.setText(String.valueOf(newFood.getServing_Size()));
            caloriesEditText.setText(String.valueOf(newFood.getCalories()));

        }


    }

    public void setNewMacro(MacroNutrient newMacro,String method) {
        this.newMacro = newMacro;


        if(method.equals("edit")){
            proteinEditText.setText(String.valueOf(newMacro.getProtein()));
            cholesterolEditText.setText(String.valueOf(newMacro.getCholesterol()));
            unsaturatedEditText.setText(String.valueOf(newMacro.getUnsaturatedFat()));
            saturatedEditText.setText(String.valueOf(newMacro.getSaturatedFat()));
            transEditText.setText(String.valueOf(newMacro.getTrans_fat()));
            fiberEditText.setText(String.valueOf(newMacro.getFiber()));
            sugarEditText.setText(String.valueOf(newMacro.getSugar()));
            sodiumEditText.setText(String.valueOf(newMacro.getSodium()));
        }


    }

}
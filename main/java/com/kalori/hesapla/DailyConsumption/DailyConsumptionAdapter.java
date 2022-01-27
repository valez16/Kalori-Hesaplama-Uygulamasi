package com.kalori.hesapla.DailyConsumption;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;
import com.kalori.hesapla.model.DatabaseHandler;
import com.kalori.hesapla.model.User_Food_Item;
import com.kalori.hesapla.model.food_Item;
import com.kalori.hesapla.model.meal;

import java.util.List;

public class DailyConsumptionAdapter extends RecyclerView.Adapter<DailyConsumptionAdapter.FoodViewHolder> {


    private  List<food_Item> mealsOfDayList;
    private DailyConsumptionAdapter adapter=this;
    private DailyConsumptionFragment dailyConsumptionFragment;
    private final DatabaseHandler dbh;

    public List<food_Item> getMealsOfDayList() {
        return mealsOfDayList;
    }
    public void addFoodItem(food_Item food){

        boolean isInList=false;

            for(int i=0; i<mealsOfDayList.size(); i++){
                if(mealsOfDayList.get(i).getName().equals(food.getName())){
                    mealsOfDayList.get(i).setServing_Size(mealsOfDayList.get(i).getServing_Size()+1);
                    isInList=true;
                    User_Food_Item tempUserFoodItem =dbh.getSpecificUserFoodItem(dailyConsumptionFragment.getDaily_consumption().getId(),food.getId(),dailyConsumptionFragment.getMeal());
                    tempUserFoodItem.setNum_Of_Serving(tempUserFoodItem.getNum_Of_Serving()+1);

                    try{
                        dbh.getUser_Food_Item_Table().update(tempUserFoodItem);
                    }catch (Exception e){

                    }
                    break;
                }
            }

            if(!isInList){
                try{
                    dbh.getUser_Food_Item_Table().create(new User_Food_Item(dailyConsumptionFragment.getDaily_consumption().getId(),food.getId(),1,dailyConsumptionFragment.getMeal()));
                    mealsOfDayList.add(food);
                }
                catch (Exception e){

                }
            }

        dailyConsumptionFragment.calculateTotalCalories(mealsOfDayList);
        notifyDataSetChanged();
    }
    public DailyConsumptionAdapter(List<food_Item> mealsOfDayList,DailyConsumptionFragment dailyConsumptionFragment) {
        dbh = new DatabaseHandler(dailyConsumptionFragment.getContext());
        this.mealsOfDayList=mealsOfDayList;
        this.dailyConsumptionFragment=dailyConsumptionFragment;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.dailyconsumption_list_item, parent, false),this
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DailyConsumptionAdapter.FoodViewHolder holder, int position) {
        holder.set(mealsOfDayList.get(position), position);
    }
    @Override
    public int getItemCount() {
        return mealsOfDayList.size();
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName_TextView;
        private TextView foodQuantity_TextView;
        private TextView foodCalorie_TextView;

        private int position;
        private int servingSize;
        private int calories;
        private food_Item food;
        private final View root;
        private meal Meal;
        private final DatabaseHandler dbh;
        private User_Food_Item userFoodItem;

        public FoodViewHolder(@NonNull final View root,final DailyConsumptionAdapter dailyConsumptionAdapter) {
            super(root);
            this.root=root;

            dbh = new DatabaseHandler(root.getContext());
            Meal=dailyConsumptionFragment.getMeal();
            foodName_TextView = root.findViewById(R.id.foodName_TextView);
            foodQuantity_TextView = root.findViewById(R.id.foodQuantity_TextView);
            foodCalorie_TextView = root.findViewById(R.id.foodCalorie_TextView);
            ImageButton increaseButton = root.findViewById(R.id.increaseQuantity_Button);
            ImageButton decreaseButton = root.findViewById(R.id.decreaseQuantity_Button);
            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        food_Item food = mealsOfDayList.get(position);
                        mealsOfDayList.get(position).setServing_Size(servingSize+1);
                        userFoodItem =dbh.getSpecificUserFoodItem(dailyConsumptionFragment.getDaily_consumption().getId(),food.getId(), Meal);
                        userFoodItem.setNum_Of_Serving(userFoodItem.getNum_Of_Serving()+1);
                        dbh.getUser_Food_Item_Table().update(userFoodItem);
                        dailyConsumptionAdapter.notifyDataSetChanged();
                        dailyConsumptionFragment.calculateTotalCalories(mealsOfDayList);
                    }catch (Exception e){

                    }
                }
            });
            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    food_Item food = mealsOfDayList.get(position);
                    userFoodItem =dbh.getSpecificUserFoodItem(dailyConsumptionFragment.getDaily_consumption().getId(),food.getId(),dailyConsumptionFragment.getMeal());
                   try {
                       if(userFoodItem.getNum_Of_Serving()==0)
                       {
                           dbh.getUser_Food_Item_Table().delete(userFoodItem);
                           mealsOfDayList.remove(position);
                       }
                       else
                       {
                           mealsOfDayList.get(position).setServing_Size(servingSize-1);
                           userFoodItem.setNum_Of_Serving(userFoodItem.getNum_Of_Serving()-1);
                           dbh.getUser_Food_Item_Table().update(userFoodItem);
                       }
                   }catch (Exception e){

                   }
                    dailyConsumptionAdapter.notifyDataSetChanged();
                    dailyConsumptionFragment.calculateTotalCalories(mealsOfDayList);

                }
            });

        }

        public void set(food_Item food, int position) {
            this.food = food;
            this.position = position;
            this.servingSize=food.getServing_Size();
            this.calories=food.getCalories();
            foodName_TextView.setText(food.getName());

            User_Food_Item userFoodItem = dbh.getSpecificUserFoodItem(dailyConsumptionFragment.getDaily_consumption().getId(),food.getId(),Meal);
            foodQuantity_TextView.setText(Integer.toString(userFoodItem.getNum_Of_Serving()));
            foodCalorie_TextView.setText(Integer.toString(food.getCalories()*userFoodItem.getNum_Of_Serving()));

        }

    }
}

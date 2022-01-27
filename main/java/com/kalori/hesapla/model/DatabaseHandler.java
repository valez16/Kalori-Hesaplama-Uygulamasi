package com.kalori.hesapla.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kalori.hesapla.sqlite.Table;
import com.kalori.hesapla.sqlite.TableFactory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CalorieCounter.db";
    public static final int DATABASE_VERSION = 1;

    //Tablolar
    private final Table<food_Item> food_Item_Table;
    private final Table<MacroNutrient> macro_Nutrient_Table;

    public Table<User_Food_Item> getUser_Food_Item_Table() {
        return user_Food_Item_Table;
    }

    private final Table<User_Food_Item> user_Food_Item_Table;

    public Table<User_Daily_Consumption> getUser_Daily_Consumption_Table() {
        return user_Daily_Consumption_Table;
    }

    private final Table<User_Daily_Consumption> user_Daily_Consumption_Table;
    private final Table<user> user_Table;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        food_Item_Table = TableFactory.makeFactory(this,food_Item.class)
                .withSeedData(SampleData.generateFoodDisplayList())
                .build();
        macro_Nutrient_Table = TableFactory.makeFactory(this,MacroNutrient.class)
                .withSeedData(SampleData.generateMacroNutrients())
                .build();
        user_Food_Item_Table = TableFactory.makeFactory(this,User_Food_Item.class)
                .withSeedData(SampleData.generateUserFoodItems())
                .build();
        user_Daily_Consumption_Table = TableFactory.makeFactory(this,User_Daily_Consumption.class)
                .withSeedData(SampleData.generate_UserDailyConsumptions())
                .build();
        user_Table = TableFactory.makeFactory(this,user.class)
                .withSeedData(SampleData.generateUsersList())
                .build();
    }

    public Table<user> get_User_table(){return user_Table;}
    public Table<food_Item> get_Food_Item_Table() {
        return food_Item_Table;
    }
    public Table<MacroNutrient> get_Macro_Nutrient_Table() {
        return macro_Nutrient_Table;
    }

    public User_Food_Item getSpecificUserFoodItem(long dailyId, long foodId, meal meal){
        User_Food_Item userFoodItem = new User_Food_Item();
        try{
            List<User_Food_Item> userFoodItems_List = user_Food_Item_Table.readAll();
            for (int i = 0; i <userFoodItems_List.size(); i++) {
                User_Food_Item tempUserFoodItem = userFoodItems_List.get(i);
                if(tempUserFoodItem.getFood_Id()==foodId && tempUserFoodItem.getMeal()==meal && tempUserFoodItem.getDaily_Id()==dailyId)
                   return tempUserFoodItem;
            }


        }catch (Exception e){

        }
        return userFoodItem;
    }

    public List<food_Item> get_FoodItem_By_UserFoodItem(long daily_Id, meal meal){
            List<food_Item> food_item_List = new ArrayList<>();

            try{
                //Tüm yemek listelerinin listesi
                List<food_Item> all_Food_Items = get_Food_Item_Table().readAll();
                //Tek bir yemekteki tüm yemeklerin listesi
                List<User_Food_Item> user_Food_Item_List= get_UserFoodItem_By_Meal_And_DailyId(daily_Id,meal);
                for (int i = 0; i < user_Food_Item_List.size(); i++) {
                    int foodId= (int) (user_Food_Item_List.get(i).getFood_Id()-1);
                    food_item_List.add(all_Food_Items.get(foodId));
                }

            }catch (Exception e){

            }
            return food_item_List;
    }
    public List<User_Food_Item> get_UserFoodItem_By_Meal_And_DailyId(long daily_Id, meal meal){
        List<User_Food_Item> full_Item_List;
        List<User_Food_Item> temp_Item_List = new ArrayList<>();
        try{

            full_Item_List = user_Food_Item_Table.readAll();

            for (int i = 0; i < full_Item_List.size(); i++) {
                User_Food_Item temp = full_Item_List.get(i);
                if(temp.getDaily_Id()==daily_Id && temp.getMeal()==meal)
                    temp_Item_List.add(temp);
            }
        }
        catch (Exception e){

        }
        return  temp_Item_List; 
    }
    public User_Daily_Consumption get_User_Daily_Consumption(String date, long user_id){
        List<User_Daily_Consumption> user_Daily_Consumptions_List;
        User_Daily_Consumption user_Daily_Consumption = new User_Daily_Consumption();
        try{
            user_Daily_Consumptions_List= user_Daily_Consumption_Table.readAll();

            for (int i = 0; i <user_Daily_Consumptions_List.size() ; i++) {
                User_Daily_Consumption temp_Daily_Consumption=user_Daily_Consumptions_List.get(i);
                if(temp_Daily_Consumption.getDate().equals(date) && temp_Daily_Consumption.getUser_id()==user_id)
                {
                    user_Daily_Consumption=temp_Daily_Consumption;
                    break;
                }
            }
        }
        catch (Exception e){

        }
        return user_Daily_Consumption;
    }

    public List<User_Food_Item> get_User_Food_Items_By_DailyId(long dailyId){
        List<User_Food_Item> user_food_items = new ArrayList<>();
        List<User_Food_Item> tmp_User_food_items;
        try{
            tmp_User_food_items = user_Food_Item_Table.readAll();
            for (User_Food_Item user_food_item : tmp_User_food_items) {
                if(user_food_item.getDaily_Id() == dailyId){
                    user_food_items.add(user_food_item);
                }
            }
        }catch (Exception e){

        }

        return user_food_items;
    }

    public food_Item get_FoodItem_By_FoodItemId(long foodItemId){
        List<food_Item> food_itemLists;

        food_Item food_item = new food_Item();

        try{
            food_itemLists = food_Item_Table.readAll();
            for ( food_Item food : food_itemLists ) {
                if(food.getId() == foodItemId)
                    food_item = food;
            }
        }catch (Exception e){

        }

        return food_item;
    }

    public MacroNutrient get_MacroNutrient_By_MacroNutrientId(long macroId){
        List<MacroNutrient> macroNutrientList;

        MacroNutrient macroNutrient = new MacroNutrient();

        try{
            macroNutrientList = macro_Nutrient_Table.readAll();
            for ( MacroNutrient macro : macroNutrientList ) {
                if(macro.getId() == macroId)
                    macroNutrient = macro;
            }
        }catch (Exception e){

        }

        return macroNutrient;
    }
    public boolean userNameExists(String username){
        try {
            List<user> userList = user_Table.readAll();
            for (int i = 0; i < userList.size(); i++) {
                user tempUser=userList.get(i);
                if(tempUser.getUsername().equals(username));
                    return true;
            }
        }catch (Exception e){

        }
        return false;
    }
    public user get_UserForLogin(String username, String password){
        try {
            List<user> userList = user_Table.readAll();

            for (int i = 0; i < userList.size(); i++) {
                user tempUser=userList.get(i);
                if(tempUser.getUsername().equals(username)&&tempUser.getPassword().equals(password))
                    return tempUser;
            }

        }catch (Exception e){

        }
        return null;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        user_Table.createTable(db);
        food_Item_Table.createTable(db);
        macro_Nutrient_Table.createTable(db);

        user_Food_Item_Table.createTable(db);
        user_Daily_Consumption_Table.createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        food_Item_Table.dropTable(db);
        user_Table.dropTable(db);
        macro_Nutrient_Table.dropTable(db);
        user_Food_Item_Table.dropTable(db);
        user_Daily_Consumption_Table.dropTable(db);
        this.onCreate(db);
    }
}
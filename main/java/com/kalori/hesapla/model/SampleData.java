package com.kalori.hesapla.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SampleData {

    public static List<user> generateUsersList(){
        List<user> userList = new ArrayList<>();
        userList.add(new user("pat","pass"));
        userList.add(new user("jan","pass"));
        userList.add(new user("alex","pass"));
        userList.add(new user("kendrick","pass"));
        return userList;
    }
    public static List<User_Daily_Consumption> generate_UserDailyConsumptions(){
        List<User_Daily_Consumption> userDailyConsumptionList = new ArrayList<>();
        try{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(today));
            c.add(Calendar.DATE,1);
            String nextDay = sdf.format(c.getTime());
            c.add(Calendar.DATE,1);
            String thirdDay=sdf.format(c.getTime());

            userDailyConsumptionList.add(new User_Daily_Consumption(1, today));
            userDailyConsumptionList.add(new User_Daily_Consumption(1, nextDay));
            userDailyConsumptionList.add(new User_Daily_Consumption(1, thirdDay));


        }
        catch (Exception e){

        }

        return userDailyConsumptionList;
    }
    public static List<User_Food_Item>generateUserFoodItems(){
        List<User_Food_Item> userFoodItemList = new ArrayList<>();

        userFoodItemList.add(new User_Food_Item(1,1,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(1,2,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(1,3,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(1,4,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(1,5,1,meal.lunch));
        userFoodItemList.add(new User_Food_Item(1,6,1,meal.lunch));
        userFoodItemList.add(new User_Food_Item(1,7,1,meal.lunch));
        userFoodItemList.add(new User_Food_Item(1,8,1,meal.dinner));
        userFoodItemList.add(new User_Food_Item(1,9,1,meal.dinner));



        userFoodItemList.add(new User_Food_Item(2,6,1,meal.dinner));
        userFoodItemList.add(new User_Food_Item(2,7,1,meal.dinner));
        userFoodItemList.add(new User_Food_Item(2,8,1,meal.lunch));
        userFoodItemList.add(new User_Food_Item(2,9,1,meal.dinner));
        userFoodItemList.add(new User_Food_Item(2,1,1,meal.snacks));
        userFoodItemList.add(new User_Food_Item(2,2,1,meal.snacks));
        userFoodItemList.add(new User_Food_Item(2,3,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(2,4,1,meal.breakfast));
        userFoodItemList.add(new User_Food_Item(2,5,1,meal.lunch));
        return  userFoodItemList;
    }
    public static List<MacroNutrient> generateMacroNutrients(){
        List<MacroNutrient> macroNutrientDisplayList = new ArrayList<>();

        MacroNutrient tofuNutrient=new MacroNutrient(14, 1.9, 0, 0, 1, 0, 0, 16);
        MacroNutrient appleNutrient=new MacroNutrient(0.3, 2.4, 10.4, 0, 0, 0, 0, 0);
        

        macroNutrientDisplayList.add(tofuNutrient);
        macroNutrientDisplayList.add(appleNutrient);
        

        return macroNutrientDisplayList;
    }

    public static List<food_Item> generateFoodDisplayList(){
        List<food_Item> foodDisplayList = new ArrayList<>();



        return foodDisplayList;
    }
    public static  ArrayList<food_Item> generateFoodForMeal(){
        int min=0;
        int max=9;
        int maxFoodNumber=4;
        int minFoodNumber=1;
        List<food_Item> displayList = generateFoodDisplayList();
        ArrayList<food_Item> mealList = new ArrayList<>();
        Random r= new Random();
        int maxFood=r.nextInt((maxFoodNumber - minFoodNumber) + 1) + minFoodNumber;
        for (int i = 0; i < maxFood; i++) {

            int randomFoodNumber=r.nextInt((max - min) + 1) + min;
            if(mealList.contains(displayList.get(randomFoodNumber)))
                i--;
            else
                mealList.add(displayList.get(randomFoodNumber));
        }
        return  mealList;
    }
    public static  List<ArrayList<food_Item>> generateFoodForDay(){

        List<ArrayList<food_Item>> mealsOfDayList = new ArrayList<>();

        Random r= new Random();
        for (int i = 0; i < 4; i++) {
            mealsOfDayList.add(generateFoodForMeal());
        }
        return  mealsOfDayList;
    }


}

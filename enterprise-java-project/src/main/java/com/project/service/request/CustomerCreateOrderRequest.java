package com.project.service.request;

import java.util.ArrayList;

//import com.project.service.util.MealIdsBody;

public class CustomerCreateOrderRequest {
    private int restaurantId;
    private ArrayList<Integer> mealIds;
    //private MealIdsBody mealIds;

    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    public ArrayList<Integer> getMealIds() {
        return mealIds;
    }
    public void setMealIds(ArrayList<Integer> mealIds) {
        this.mealIds = mealIds;
    }

}

package com.project.service.request;

import java.util.ArrayList;

public class CustomerOrderRequest {
    private int restaurantId;
    private ArrayList<Integer> mealIds;

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

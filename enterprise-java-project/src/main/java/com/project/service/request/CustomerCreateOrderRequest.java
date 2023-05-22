package com.project.service.request;

import java.util.List;

public class CustomerCreateOrderRequest {
    private int restaurantId;
    private List<Integer> mealIds;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Integer> getMealIds() {
        return mealIds;
    }

    public void setMealIds(List<Integer> mealIds) {
        this.mealIds = mealIds;
    }
}

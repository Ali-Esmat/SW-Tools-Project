package com.project.service.request;

import java.util.ArrayList;

//import com.project.service.util.MealIdsBody;

public class CustomerEditOrderRequest {
    private int orderId;
    //TODO: test if arraylist works with json body
    private ArrayList<Integer> mealIds;
    //private MealIdsBody mealIds;
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public ArrayList<Integer> getMealIds() {
        return mealIds;
    }
    public void setMealIds(ArrayList<Integer> mealIds) {
        this.mealIds = mealIds;
    }

}
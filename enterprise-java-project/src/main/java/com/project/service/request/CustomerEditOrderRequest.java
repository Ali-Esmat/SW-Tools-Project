package com.project.service.request;

import java.util.List;

public class CustomerEditOrderRequest {
    private List<Integer> mealIds;

    public List<Integer> getMealIds() {
        return mealIds;
    }

    public void setMealIds(List<Integer> mealIds) {
        this.mealIds = mealIds;
    }
}

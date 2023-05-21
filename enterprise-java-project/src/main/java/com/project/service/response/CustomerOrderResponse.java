package com.project.service.response;

import java.util.Date;

public class CustomerOrderResponse {
    private Date date;
	private String restaurantName; 
	private String meals;
	private float deliveryFees;
    private String runnerName;
    private float totalReciptValue;
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getMeals() {
        return meals;
    }
    public void setMeals(String meals) {
        this.meals = meals;
    }
    public float getDeliveryFees() {
        return deliveryFees;
    }
    public void setDeliveryFees(float deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    public String getRunnerName() {
        return runnerName;
    }
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }
    public float getTotalReciptValue() {
        return totalReciptValue;
    }
    public void setTotalReciptValue(float totalReciptValue) {
        this.totalReciptValue = totalReciptValue;
    }

    

}

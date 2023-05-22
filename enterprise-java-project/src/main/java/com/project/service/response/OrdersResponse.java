package com.project.service.response;

import java.util.Date;
import java.util.List;

import com.project.enums.OrderStatusEnum;
import com.project.model.Orders;
import com.project.service.util.ServiceUtil;

public class OrdersResponse {
    private Date date;
    private String restaurantName;
    private List<MealResponse> meals;
    private float deliveryFees;
    private String runnerName;
    private OrderStatusEnum status;

    public OrdersResponse(Orders order) {
        this.date = order.getDate();
        this.restaurantName = order.getRestaurant().getName();
        this.meals = ServiceUtil.entitiesToResponses(order.getMeals(), (m) -> new MealResponse(m));
        this.deliveryFees = order.getRunner().getDeliveryFees();
        this.runnerName = order.getRunner().getUser().getName();
        this.status = order.getStatus();
    }

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

    public List<MealResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<MealResponse> meals) {
        this.meals = meals;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}

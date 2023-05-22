package com.project.service.response;

import com.project.model.Meal;

public class MealResponse {
    private int id;
    private String name;
    private double price;

    public MealResponse(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.price = meal.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

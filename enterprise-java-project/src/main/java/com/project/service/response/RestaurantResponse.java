package com.project.service.response;

import java.util.List;
import java.util.Set;

import com.project.model.Meal;
import com.project.model.Restaurant;
import com.project.service.util.ServiceUtil;

public class RestaurantResponse {
    private int id;
    private String name;
    private RestaurantOwnerResponse owner;
    private List<MealResponse> meals;

    public RestaurantResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.owner = new RestaurantOwnerResponse(restaurant.getOwner().getUser().getName());
        Set<Meal> meals = restaurant.getMeals();
        this.meals = ServiceUtil.entitiesToResponses(meals, (m) -> new MealResponse(m));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantOwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(RestaurantOwnerResponse owner) {
        this.owner = owner;
    }

    public List<MealResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<MealResponse> meals) {
        this.meals = meals;
    }
}

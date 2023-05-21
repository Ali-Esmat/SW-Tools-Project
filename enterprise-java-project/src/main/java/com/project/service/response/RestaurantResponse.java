package com.project.service.response;

import com.project.model.Restaurant;

public class RestaurantResponse {
    private Restaurant restaurant;

    public RestaurantResponse(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

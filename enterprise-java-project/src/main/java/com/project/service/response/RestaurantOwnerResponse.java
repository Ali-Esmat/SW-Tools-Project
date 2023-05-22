package com.project.service.response;

public class RestaurantOwnerResponse {
    private String name;

    public RestaurantOwnerResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

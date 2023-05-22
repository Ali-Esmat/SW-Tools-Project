package com.project.service.response;

import com.project.model.Customer;

public class CustomerResponse {
    private int userId;
    private String name;

    public CustomerResponse(Customer customer) {
        this.userId = customer.getUser().getId();
        this.name = customer.getUser().getName();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

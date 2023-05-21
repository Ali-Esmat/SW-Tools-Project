package com.project.service.request;

public class RunnerRequest extends UserRequest {
    private double deliveryFees;

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
}

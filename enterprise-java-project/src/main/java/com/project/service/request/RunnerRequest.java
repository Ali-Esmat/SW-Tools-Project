package com.project.service.request;

public class RunnerRequest extends UserRequest {
    private float deliveryFees;

    public float getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(float deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
}
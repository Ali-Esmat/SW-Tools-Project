package com.project.service.request;

import com.advancedtools.project.enums.RoleEnum;

public class RunnerRequest extends UserRequest {
    private double deliveryFees;
    private RoleEnum status;

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    
    public RoleEnum getRunnerStatus() {
        return status;
    }

    public void setRunnerStatus(RoleEnum status) {
        this.status = status;
    }
}

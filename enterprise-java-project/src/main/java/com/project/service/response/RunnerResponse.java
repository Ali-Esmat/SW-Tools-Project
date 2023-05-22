package com.project.service.response;

import com.project.enums.RunnerStatusEnum;
import com.project.model.Runner;

public class RunnerResponse {
    private int userId;
    private String name;
    private float deliveryFees;
    private RunnerStatusEnum status;

    public RunnerResponse(Runner runner) {
        this.userId = runner.getUser().getId();
        this.name = runner.getUser().getName();
        this.deliveryFees = runner.getDeliveryFees();
        this.status = runner.getStatus();
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

    public float getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(float deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public RunnerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RunnerStatusEnum status) {
        this.status = status;
    }

}

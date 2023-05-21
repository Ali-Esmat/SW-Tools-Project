package com.project.service.request;

import com.project.enums.RunnerStatusEnum;

public class RunnerRequest extends UserRequest {
    private double deliveryFees;
    private RunnerStatusEnum runnerStatus;

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    
    public RunnerStatusEnum getRunnerStatus() {
        return runnerStatus;
    }

    public void setRunnerStatus(RunnerStatusEnum runnerStatus) {
        this.runnerStatus = runnerStatus;
    }
}

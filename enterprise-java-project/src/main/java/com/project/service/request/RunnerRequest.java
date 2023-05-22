package com.project.service.request;

import com.project.enums.RunnerStatusEnum;

public class RunnerRequest extends UserRequest {
    private float deliveryFees;
    private RunnerStatusEnum runnerStatus;

    public float getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(float deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    
    public RunnerStatusEnum getRunnerStatus() {
        return runnerStatus;
    }

    public void setRunnerStatus(RunnerStatusEnum runnerStatus) {
        this.runnerStatus = runnerStatus;
    }
}

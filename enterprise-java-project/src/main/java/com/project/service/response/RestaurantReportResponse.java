package com.project.service.response;

public class RestaurantReportResponse {
    private int id;
    private double earns;
    private int noCompletedOrders;
    private int noCancelledOrders;

    public RestaurantReportResponse(int id, double earns, int noCompletedOrders, int noCancelledOrders) {
        this.id = id;
        this.earns = earns;
        this.noCompletedOrders = noCompletedOrders;
        this.noCancelledOrders = noCancelledOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEarns() {
        return earns;
    }

    public void setEarns(double earns) {
        this.earns = earns;
    }

    public int getNoCompletedOrders() {
        return noCompletedOrders;
    }

    public void setNoCompletedOrders(int noCompletedOrders) {
        this.noCompletedOrders = noCompletedOrders;
    }

    public int getNoCancelledOrders() {
        return noCancelledOrders;
    }

    public void setNoCancelledOrders(int noCancelledOrders) {
        this.noCancelledOrders = noCancelledOrders;
    }
}

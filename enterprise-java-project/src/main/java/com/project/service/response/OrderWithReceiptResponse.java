package com.project.service.response;

import com.project.model.Orders;

public class OrderWithReceiptResponse extends OrdersResponse {
    private float totalReceiptValue;

    public OrderWithReceiptResponse(Orders order, float totalReceiptValue) {
        super(order);
        this.totalReceiptValue = totalReceiptValue;
    }

    public float getTotalReceiptValue() {
        return totalReceiptValue;
    }

    public void setTotalReceiptValue(float totalReceiptValue) {
        this.totalReceiptValue = totalReceiptValue;
    }
}

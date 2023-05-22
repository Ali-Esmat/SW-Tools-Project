package com.project.service.response;

import java.util.List;
import java.util.Set;

import com.project.model.Orders;
import com.project.service.util.ServiceUtil;

public class MultipleOrdersResponse {
    private List<OrdersResponse> orders;

    public MultipleOrdersResponse(Set<Orders> orders) {
        this.orders = ServiceUtil.entitiesToResponses(orders, (o) -> new OrdersResponse(o));
    }

    public List<OrdersResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersResponse> orders) {
        this.orders = orders;
    }
}

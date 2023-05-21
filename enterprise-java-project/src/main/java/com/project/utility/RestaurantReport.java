package com.project.utility;

import java.util.Set;

import com.project.enums.OrderStatusEnum;
import com.project.model.Orders;
import com.project.model.Restaurant;

public class RestaurantReport {

    public double totalEarinings;
    public int numberOfCompletedOrders;
    public int numberOfCancelledOrders;

    public RestaurantReport(Restaurant restaurant) {
        Set<Orders> orders = restaurant.getOrders();
        for (Orders order : orders) {
            if (order.status == OrderStatusEnum.DELIVERED) {
                totalEarinings += order.getTotalPrice();
                numberOfCompletedOrders++;
            } else if (order.status == OrderStatusEnum.CANCELED) {
                numberOfCancelledOrders++;
            }
        }
    }

}

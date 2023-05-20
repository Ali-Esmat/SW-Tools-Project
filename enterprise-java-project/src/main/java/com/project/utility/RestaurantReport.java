package com.project.utility;

import java.util.Set;

import com.project.enums.OrderStatus;
import com.project.model.Orders;
import com.project.model.Restaurant;

public class RestaurantReport {

    public float totalEarinings;
    public int numberOfCompletedOrders;
    public int numberOfCancelledOrders;

    public RestaurantReport(Restaurant restaurant){
        Set<Orders> orders = restaurant.getOrders();
        for(Orders order:orders){
            if (order.status == OrderStatus.DELIVERED){
                totalEarinings += order.getTotalPrice();
                numberOfCompletedOrders++;
            }
            else if (order.status == OrderStatus.CANCELED){
                numberOfCancelledOrders++;
            }
        }
    }
    
}

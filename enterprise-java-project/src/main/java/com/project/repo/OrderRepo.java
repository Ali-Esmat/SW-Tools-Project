package com.project.repo;

import java.util.Date;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.project.enums.OrderStatusEnum;
import com.project.model.Customer;
import com.project.model.Meal;
import com.project.model.Orders;
import com.project.model.Restaurant;
import com.project.model.Runner;

@Stateless
public class OrderRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    public Orders createOrder(Runner runner, OrderStatusEnum status, Set<Meal> meals, Restaurant restaurant,
            Customer customer) {
        Orders order = new Orders();
        order.setRunner(runner);
        order.setStatus(status);
        order.setDate(new Date());
        order.setMeals(meals);
        order.setRestaurant(restaurant);
        order.setCustomer(customer);
        em.persist(order);
        return order;
    }

    public void EditOrder(Orders order) {
    }

}

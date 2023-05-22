package com.project.repo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    @Inject
    CustomerRepo customerRepo;

    @Inject
    MealRepo mealRepo;

    public Orders createOrder(Runner runner, OrderStatusEnum status, Restaurant restaurant,
            Customer customer) {
        Orders order = new Orders();
        order.setRunner(runner);
        order.setStatus(status);
        order.setDate(new Date());
        order.setRestaurant(restaurant);
        order.setCustomer(customer);
        em.persist(order);
        return order;
    }

    public Orders editOrderMeals(int orderId, List<Integer> mealIds) {
        Set<Meal> meals = new HashSet<Meal>();
        for (int mealId : mealIds) {
            Meal meal = mealRepo.getMealById(mealId);
            meals.add(meal);
        }
        Orders order = getOrderById(orderId);
        order.getMeals().forEach((m) -> m.getOrders().remove(order));
        order.setMeals(meals);
        order.getMeals().forEach((m) -> m.getOrders().add(order));
        return order;
    }

    public Orders setOrderStatus(int orderId, OrderStatusEnum status) {
        Orders order = getOrderById(orderId);
        order.setStatus(status);
        return order;
    }

    public Orders getOrderById(int orderId) {
        TypedQuery<Orders> orderQuery = em.createQuery("select o from Orders o where o.id = :id", Orders.class);
        orderQuery.setParameter("id", orderId);
        return orderQuery.getSingleResult();

    }

    public Set<Orders> getCustomerOrders(int customerId) {
        Customer customer = customerRepo.getCustomerById(customerId);
        return customer.getOrders();
    }
}

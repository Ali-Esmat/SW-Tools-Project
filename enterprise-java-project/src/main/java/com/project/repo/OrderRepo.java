package com.project.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
    RunnerRepo runnerRepo;

    @Inject
    MealRepo mealRepo;

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

    public Orders editOrder(int orderId, ArrayList<Integer> mealIds) {

        Set<Meal> meals = new HashSet<Meal>();
        for (int mealId : mealIds) {
            Meal meal = mealRepo.getMealById(mealId);
            meals.add(meal);
        }
        Orders order = getOrderById(orderId);
        order.setMeals(meals);
        return order;
    }

    public Orders setOrderStatus(int orderId, OrderStatusEnum status) {
        Orders order = getOrderById(orderId);
        order.setStatus(status);
        return order;
    }

    public Orders getOrderById(int orderId) {
        TypedQuery<Orders> orderQuery = em.createQuery("select o from Order o where o.id = :id", Orders.class);
        orderQuery.setParameter("id", orderId);
        return orderQuery.getSingleResult();

    }

    // Can these functions become more generic ?
    // will this return type cause an error for the JSON body ?
    // TODO: test the api related to this query

    public Set<Orders> getCustomerOrders(int customerId) {
        Customer customer = customerRepo.getCustomerById(customerId);
        return customer.getOrders();
    }

    public Set<Orders> getRunnerOrders(int runnerId) {
        Runner runner = runnerRepo.getRunnerById(runnerId);
        return runner.getOrders();
    }
}

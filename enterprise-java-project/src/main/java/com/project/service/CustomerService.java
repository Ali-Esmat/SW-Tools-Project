package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.OrderStatusEnum;
import com.project.enums.RoleEnum;
import com.project.enums.RunnerStatusEnum;
import com.project.model.Customer;
import com.project.model.Meal;
import com.project.model.Orders;
import com.project.model.Restaurant;
import com.project.model.Runner;
import com.project.service.request.CustomerOrderRequest;
import com.project.service.response.CustomerOrderResponse;

@RequestScoped
@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {
    @PersistenceContext(unitName = "project")
    private EntityManager em;
    //@Context SecurityContext context.gerUserPrincipal.getName()
    @POST
    @RolesAllowed(RoleEnum.Constants.CUSTOMER_VALUE)
    public CustomerOrderResponse makeOrder(CustomerOrderRequest request, @Context SecurityContext context){

        Orders order = new Orders();
        //retrieve first runner with status avaliable via query (mark runner as busy)
        Runner runner = em.createQuery("SELECT r FROM Runner r WHERE r.status = :status", Runner.class)
        .setParameter("status", RunnerStatusEnum.AVAILABLE ).getSingleResult();
        runner.setStatus(RunnerStatusEnum.BUSY);
        
        // Runner attribute in order
        order.setRunner(runner);
        

        // enumStatus attribute in order 
        order.setStatus(OrderStatusEnum.PREPARING);
        
        // retrieve Meal instances via query (using meal id in a for loop)
        ArrayList<Integer> mealIds = request.getMealIds();
        TypedQuery<Meal> mealQuery = em.createQuery("SELECT m FROM Meal m where m.id = ?1 ", Meal.class);
        Set<Meal> meals = new HashSet<Meal>();
        Meal tmpMeal = new Meal();
        
        for (int i = 0; i < request.getMealIds().size(); i++ ){
            mealQuery.setParameter(1, request.getMealIds().get(i));
            tmpMeal = mealQuery.getSingleResult();
            meals.add(tmpMeal);
        } 

        // retrieve Restaurant instance from one of the meals retrieved by the query
        Restaurant restaurant = tmpMeal.getRestaurant();

        // Restaurant attribute in order
        order.setRestaurant(restaurant);
        
        // Set<Meals> attribute in order
        order.setMeals(meals);
        
        TypedQuery<Customer> customerQuery = em.createQuery("SELECT m FROM Customer m where m.id = ?1 ", Customer.class);
        customerQuery.setParameter(1, Integer.valueOf(context.getUserPrincipal().getName()));
        Customer customer = customerQuery.getSingleResult();

        // Customer attribute in order
        order.setCustomer(customer);

        Date date = new Date();
        
        // Date attribute in order
        order.setDate(date);

        // persist the order using the order repo


        /***********************************/

        // return the response message 

    }
    
    
}

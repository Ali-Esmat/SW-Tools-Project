package com.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
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
import com.project.repo.OrderRepo;
import com.project.repo.RunnerRepo;
import com.project.repo.CustomerRepo;
import com.project.repo.MealRepo;
import com.project.service.request.CustomerOrderRequest;
import com.project.service.response.CustomerOrderResponse;

@RequestScoped
@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {
    @Inject
    RunnerRepo runnerRepo;

    @Inject
    OrderRepo orderRepo;

    @Inject
    MealRepo mealRepo;

    @Inject
    CustomerRepo customerRepo;

    @POST
    @RolesAllowed(RoleEnum.Constants.CUSTOMER_VALUE)
    public CustomerOrderResponse makeOrder(CustomerOrderRequest request, @Context SecurityContext context) {

        float mealTotal = 0;
        // retrieve first runner with status avaliable via query (mark runner as busy)
        Runner runner = runnerRepo.getFirstFreeRunner();
        if (runner == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("No runner is currently avaliable"));
        runner.setStatus(RunnerStatusEnum.BUSY);

        ArrayList<Integer> mealIds = request.getMealIds();
        Set<Meal> meals = new HashSet<Meal>();
        Meal tmpMeal = new Meal();

        // retrieve Meal instances via query (using meal id in a for loop)
        for (int i = 0; i < mealIds.size(); i++) {
            tmpMeal = mealRepo.getMealById(mealIds.get(i));
            mealTotal += tmpMeal.getPrice();
            meals.add(tmpMeal);

        }
        // retrieve Restaurant instance from one of the meals retrieved by the query
        Restaurant restaurant = tmpMeal.getRestaurant();
        /*
         * if (restaurant == null)
         * throw new BadRequestException(ServiceUtil.
         * createErrorResponse("No restaurant is providing these meals"));
         */

        // retrieve Customer instance from the database using the id from the user
        // context
        Customer customer = customerRepo.selectCustomerById(Integer.valueOf(context.getUserPrincipal().getName()));

        // persist the order using the order repo

        Orders order = orderRepo.createOrder(runner, OrderStatusEnum.PREPARING, meals, restaurant, customer);

        /***********************************/

        // return the response message

        CustomerOrderResponse response = new CustomerOrderResponse();
        response.setDate(order.getDate());
        response.setRestaurantName(order.getRestaurant().getName());
        response.setMeals(order.getMeals());
        response.setDeliveryFees(runner.getDeliveryFees());
        response.setRunnerName(runner.getUser().getName());
        response.setTotalReciptValue(mealTotal += runner.getDeliveryFees());

        return response;

    }

}

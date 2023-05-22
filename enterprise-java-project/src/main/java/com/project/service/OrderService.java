package com.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.project.repo.RestaurantRepo;
import com.project.repo.MealRepo;
import com.project.service.request.CustomerCreateOrderRequest;
import com.project.service.request.CustomerEditOrderRequest;
import com.project.service.response.OrdersResponse;
import com.project.service.util.ServiceUtil;

@RequestScoped
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(RoleEnum.Constants.CUSTOMER_VALUE)
public class OrderService {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    @Inject
    RunnerRepo runnerRepo;

    @Inject
    OrderRepo orderRepo;

    @Inject
    MealRepo mealRepo;

    @Inject
    CustomerRepo customerRepo;

    @Inject
    RestaurantRepo restaurantRepo;

    @POST
    public OrdersResponse makeOrder(CustomerCreateOrderRequest request, @Context SecurityContext context) {
        // retrieve first runner with status avaliable via query (mark runner as busy)
        Runner runner = runnerRepo.getFirstFreeRunner();
        if (runner == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("No runner is currently avaliable"));
        runnerRepo.setRunnerStatus(runner.getId(),RunnerStatusEnum.BUSY);

        ArrayList<Integer> mealIds = request.getMealIds();
        Set<Meal> meals = new HashSet<Meal>();
        Meal tmpMeal = new Meal();

        // retrieve Meal instances via query (using meal id in a for loop)
        for (int i = 0; i < mealIds.size(); i++) {
            tmpMeal = mealRepo.getMealById(mealIds.get(i));
            if (tmpMeal.getRestaurant().getId() != request.getRestaurantId())
                throw new BadRequestException(ServiceUtil.createErrorResponse(
                        "This restaurant does not provide meal with id " + tmpMeal.getRestaurant().getId()));
            meals.add(tmpMeal);
        }
        // retrieve Restaurant instance from one of the meals retrieved by the query
        Restaurant restaurant = restaurantRepo.getRestaurantById(request.getRestaurantId());
        if (restaurant == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("No restaurant with the specified Id."));

        // retrieve Customer instance from the database using the id from the user
        // context
        Customer customer = customerRepo.getCustomerById(ServiceUtil.getIdFromContext(context));
        Orders order = orderRepo.createOrder(runner, OrderStatusEnum.PREPARING, meals, restaurant, customer);
        float totalPrice = calculateOrderPrice(order);
        return new OrdersResponse(order, totalPrice);
    }

    public float calculateOrderPrice(Orders order) {
        Set<Meal> meals = order.getMeals();
        float mealTotal = 0;
        for (Meal meal : meals) {
            mealTotal += meal.getPrice();
        }
        mealTotal += order.getRunner().getDeliveryFees();
        return mealTotal;
    }

    // For customer to change items in order
    @PUT
    public OrdersResponse editOrder(CustomerEditOrderRequest request, @Context SecurityContext context) {

        Orders order = orderRepo.getOrderById(request.getOrderId());

        if (order.getStatus() == OrderStatusEnum.CANCELED) {
            throw new BadRequestException(ServiceUtil.createErrorResponse("The selected order is cancelled"));
        }
        if (order.getStatus() == OrderStatusEnum.DELIVERED) {
            throw new BadRequestException(ServiceUtil.createErrorResponse("The selected order is delivered"));
        }

        Orders editedOrder = orderRepo.editOrder(request.getOrderId(), request.getMealIds());
        float editedOrderTotal = calculateOrderPrice(editedOrder);

        return new OrdersResponse(editedOrder, editedOrderTotal);
    }

    public void setOrderStatus(int runnerId, int orderId, OrderStatusEnum status) {
        Orders order = orderRepo.getOrderById(orderId);
        if (order == null || order.getRunner().getUser().getId() != runnerId)
            throw new BadRequestException(ServiceUtil.createErrorResponse("Can't find the specified order"));
        orderRepo.setOrderStatus(orderId, status);
        runnerRepo.setRunnerStatus(runnerId, RunnerStatusEnum.AVAILABLE);
    }

    @POST
    @RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
    @Path("/{id}/deliver")
    public void deliverOrder(@Context SecurityContext context, @PathParam("id") int orderId) {
        setOrderStatus(ServiceUtil.getIdFromContext(context), orderId, OrderStatusEnum.DELIVERED);
    }

    // put because order is not deleted from the db
    @POST
    @Path("/{id}/cancel")
    public void cancelOrder(@Context SecurityContext context, int orderId) {
        Orders order = orderRepo.getOrderById(orderId);
        if (order == null || order.getCustomer().getUser().getId() != ServiceUtil.getIdFromContext(context))
            throw new BadRequestException(ServiceUtil.createErrorResponse("This order doesn't exist"));
        int runnerId = order.getRunner().getUser().getId();
        setOrderStatus(runnerId, orderId, OrderStatusEnum.CANCELED);

    }

    //@GET
    //public  getCustomerOrders

}

package com.project.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.project.service.response.MultipleOrdersResponse;
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

    /*
     * No need ot give it a customer id as mentioned in the specs since we already
     * have the customer in context
     */
    @GET
    public MultipleOrdersResponse getOrdersForCustomer(@Context SecurityContext context) {
        Customer customer = customerRepo.getCustomerById(ServiceUtil.getIdFromContext(context));
        return new MultipleOrdersResponse(customer.getOrders());
    }

    private void verifyMealIdsAgainstRestaurant(int restaurantId, List<Integer> mealIds) {
        if (mealIds == null || mealIds.isEmpty()) {
            throw new BadRequestException(ServiceUtil.createErrorResponse("No meals were provided"));
        }

        for (int mealId : mealIds) {
            Meal meal = mealRepo.getMealById(mealId);
            if (meal == null || meal.getRestaurant().getId() != restaurantId)
                throw new BadRequestException(ServiceUtil.createErrorResponse(
                        "This restaurant does not provide meal with id " + mealId));
        }
    }

    @POST
    public OrdersResponse makeOrder(@Context SecurityContext context, CustomerCreateOrderRequest request) {
        Runner runner = runnerRepo.getFirstFreeRunner();
        if (runner == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("No runner is currently available"));

        List<Integer> mealIds = request.getMealIds();
        verifyMealIdsAgainstRestaurant(request.getRestaurantId(), mealIds);

        Restaurant restaurant = restaurantRepo.getRestaurantById(request.getRestaurantId());
        if (restaurant == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("No restaurant with the specified Id."));

        Customer customer = customerRepo.getCustomerById(ServiceUtil.getIdFromContext(context));
        Orders order = orderRepo.createOrder(runner, OrderStatusEnum.PREPARING, restaurant, customer);
        Orders orderWithMeal = orderRepo.editOrderMealsAndPrice(order.getId(), mealIds, runner.getDeliveryFees());
        runnerRepo.setRunnerStatus(runner.getUser().getId(), RunnerStatusEnum.BUSY);
        return new OrdersResponse(orderWithMeal);
    }

    @PUT
    @Path("{id}")
    public OrdersResponse editOrder(@Context SecurityContext context, @PathParam("id") int id,
            CustomerEditOrderRequest request) {
        Orders order = orderRepo.getOrderById(id);

        if (order == null || order.getCustomer().getUser().getId() != ServiceUtil.getIdFromContext(context))
            throw new BadRequestException(ServiceUtil.createErrorResponse("No order exists with such id"));

        if (order.getStatus() != OrderStatusEnum.PREPARING)
            throw new BadRequestException(ServiceUtil.createErrorResponse("The specified order can't be edited"));

        List<Integer> mealIds = request.getMealIds();
        verifyMealIdsAgainstRestaurant(order.getRestaurant().getId(), mealIds);
        Orders editedOrder = orderRepo.editOrderMealsAndPrice(id, mealIds, order.getRunner().getDeliveryFees());
        return new OrdersResponse(editedOrder);
    }

    public void setOrderStatus(int runnerUserId, int orderId, OrderStatusEnum status) {
        Orders order = orderRepo.getOrderById(orderId);
        if (order == null || order.getStatus() != OrderStatusEnum.PREPARING
                || order.getRunner().getUser().getId() != runnerUserId)
            throw new BadRequestException(ServiceUtil.createErrorResponse("Can't find the specified order"));
        orderRepo.setOrderStatus(orderId, status);
        runnerRepo.setRunnerStatus(runnerUserId, RunnerStatusEnum.AVAILABLE);
    }

    @POST
    @RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
    @Path("{id}/deliver")
    public void deliverOrder(@Context SecurityContext context, @PathParam("id") int orderId) {
        setOrderStatus(ServiceUtil.getIdFromContext(context), orderId, OrderStatusEnum.DELIVERED);
    }

    @POST
    @Path("{id}/cancel")
    public void cancelOrder(@Context SecurityContext context, @PathParam("id") int orderId) {
        Orders order = orderRepo.getOrderById(orderId);
        if (order == null || order.getCustomer().getUser().getId() != ServiceUtil.getIdFromContext(context))
            throw new BadRequestException(ServiceUtil.createErrorResponse("This order doesn't exist"));
        int runnerId = order.getRunner().getUser().getId();
        setOrderStatus(runnerId, orderId, OrderStatusEnum.CANCELED);
    }
}

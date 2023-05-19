package com.project.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    // Create restaurant menu
    // Edit restaurant: change menu meals for each restaurant
    // Get restaurant details by id
    // Create restaurant report: given a restaurant id print
    // how much the restaurant earns (summation of total amount of all completed
    // orders) , Number of completed orders, Number of canceled orders
    
}

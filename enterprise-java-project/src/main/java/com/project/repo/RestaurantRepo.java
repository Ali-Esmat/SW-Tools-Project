package com.project.repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.model.Restaurant;
import com.project.model.RestaurantOwner;
import com.project.model.User;

@Stateless
public class RestaurantRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    private RestaurantOwner getRestaurantOwnerById(int ownerId) {
        TypedQuery<User> userQuery = em.createQuery("select u from User u where u.id = :id", User.class);
        userQuery.setParameter("id", ownerId);
        User user = userQuery.getSingleResult();
        return user.getRestaurantOwner();
    }

    public Restaurant createRestaurant(int ownerId, String name) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        RestaurantOwner ro = getRestaurantOwnerById(ownerId);
        restaurant.setOwner(ro);
        em.persist(restaurant);
        return restaurant;
    }

    public Restaurant getRestaurantForRestaurantOwner(int ownerId) {
        RestaurantOwner ro = getRestaurantOwnerById(ownerId);
        return ro.getRestaurant();
    }

    public List<Restaurant> getAllRestaurants() {
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }

    public Restaurant getRestaurantById(int restaurantId) {
        TypedQuery<Restaurant> restaurantQuery = em.createQuery("select r from Restaurant r where r.id = :id",
                Restaurant.class);
        restaurantQuery.setParameter("id", restaurantId);
        return RepoUtil.getFirstResultOrNull(restaurantQuery);
    }
}

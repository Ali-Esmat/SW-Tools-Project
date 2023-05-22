package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.model.Meal;
import com.project.model.Orders;
import com.project.model.Restaurant;

@Stateless
public class MealRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    private void setMealDetails(Meal meal, String name, double price) {
        meal.setName(name);
        meal.setPrice(price);
    }

    public Meal createMealForRestaurant(Restaurant restaurant, String name, double price) {
        Meal meal = new Meal();
        setMealDetails(meal, name, price);
        meal.setRestaurant(restaurant);
        em.persist(meal);
        return meal;
    }

    public Meal getMealById(int id) {
        TypedQuery<Meal> query = em.createQuery("select m from Meal m where m.id = :id", Meal.class);
        query.setParameter("id", id);
        return RepoUtil.getFirstResultOrNull(query);
    }

    public void deleteMealById(int id) {
        Meal meal = getMealById(id);
        em.remove(meal);
    }

    public Meal editMealById(int id, String name, double price) {
        Meal meal = getMealById(id);
        setMealDetails(meal, name, price);
        return meal;
    }

}

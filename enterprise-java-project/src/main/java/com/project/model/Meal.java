package com.project.model;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private float price;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Name of foreign key column in Meal table
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
        name = "MealXOrders",
        joinColumns = @JoinColumn(name = "meal_id"), // Name of foreign key column to Meal table in MealXOrders table in database
        inverseJoinColumns = @JoinColumn(name = "order_id") // Name of foreign key column to Order table in MealXOrders table in database
    )
    private Set<Orders> orders;

    public Meal(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public Restaurant getRestaurantId(){
        return restaurant;
    }

    public void setRestaurantId(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}

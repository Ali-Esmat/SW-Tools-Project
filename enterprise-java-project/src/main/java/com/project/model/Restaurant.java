package com.project.model;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "owner_id") // This is a database column
    private User owner;
    
    @OneToMany(mappedBy = "restaurant")
    private Set<Meal> meals;
    
    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> orders;

    public Restaurant(){}

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public User getOwner(){
        return this.owner;
    }
    public void setOwnerId(User owner){
        this.owner = owner;
    }
    
    public Set<Meal> getMeals(){
        return meals;
    } 
    public void setMeals(Set<Meal> meals){
        this.meals = meals;
    }

    public Set<Orders> getOrders(){
        return orders;
    }
    public void setOrders(Set<Orders> orders){
        this.orders = orders; 
    }

}
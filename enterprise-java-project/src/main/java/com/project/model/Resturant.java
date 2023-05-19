package com.project.model;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Resturant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private int ownerId;
    
    @OneToMany(mappedBy = "restaurantId")
    private ArrayList<Meal> meals;
    
    @OneToMany(mappedBy = "restaurantId")
    private ArrayList<Order> orders;

    public Resturant(){}

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

    public int getOwnerId(){
        return this.ownerId;
    }
    public void setOwnerId(int OwnerId){
        this.ownerId = OwnerId;
    }
    
    public ArrayList<Meal> getMeals(){
        return meals;
    } 
    public void setMeals(ArrayList<Meal> meals){
        this.meals = meals;
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }
    public void setOrders(ArrayList<Order> orders){
        this.orders = orders; 
    }

}

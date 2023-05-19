package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToOne(mappedBy = "owner") 
    private Restaurant restaurant;
    // Attribute Role has been removed

    public User(){}

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

    public Restaurant getRestaurant(){
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
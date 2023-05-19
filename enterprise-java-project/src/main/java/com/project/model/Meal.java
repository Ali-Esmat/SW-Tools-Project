package com.project.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private float price;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // This represents the database column
    private Restaurant restaurant;

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

package com.project.model;

import com.project.enums.OrderStatus;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "runner_id") // Name of foreign key column in Orders table in database
    private Runner runner;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Name of foreign key column in Orders table in database
    private Restaurant restaurant;
    public OrderStatus status;

    @ManyToMany(mappedBy = "orders")
    private Set<Meal> meals;

    public Orders(){}

    public int getId(){
        return id;
    }
    public void setId(int Id){
        this.id = Id;
    }
    
    public Set<Meal> getItems(){
        return meals;
    }
    public void setItems(Set<Meal> meals){
        this.meals = meals;
    }

    public float getTotalPrice(){
        return totalPrice; 
    }
    public void setTotalPrice(float total_price){
        totalPrice = total_price; 
    }

    public Runner getRunnerId(){
        return runner;
    }

    public void setRunnerId(Runner runner){
        this.runner = runner;
    }
    
    public Restaurant getRestaurantId(){
        return restaurant;
    }
    public void setRestaurantId(Restaurant restaurant){
        this.restaurant = restaurant;
    }

}

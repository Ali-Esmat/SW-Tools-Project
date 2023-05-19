package com.project.model;

import com.project.enums.OrderStatus;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ArrayList<Meal> items;
    private float totalPrice;
    @OneToOne
    @JoinColumn(name = "runner_id") // Database Name
    private Runner runner;
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Database Name
    private Restaurant restaurant;
    public OrderStatus status;

    public Orders(){}

    public int getId(){
        return id;
    }
    public void setId(int Id){
        this.id = Id;
    }
    
    public ArrayList<Meal> getItems(){
        return items;
    }
    public void setItems(ArrayList<Meal> items){
        this.items = items;
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

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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ArrayList<Meal> items;
    private float totalPrice;
    @OneToOne(optional = false)
    @JoinColumn(name = "runner_id") // Database Name
    private int runnerId;
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Database Name
    private int restaurantId;
    public OrderStatus status;

    public Order(){}

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

    public int getRunnerId(){
        return runnerId;
    }

    public void setRunnerId(int runnerId){
        this.runnerId = runnerId;
    }
    
    public int getRestaurantId(){
        return restaurantId;
    }
    public void setRestaurantId(int id){
        this.id = id;
    }

}

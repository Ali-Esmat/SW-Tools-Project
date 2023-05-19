package com.project.model;

import com.project.enums.RunnerStatus;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float delivery_fees;
    public RunnerStatus status;
    @OneToMany(mappedBy = "runner")
    private Set<Orders> order;

    public Runner(){}

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
    
    public float getDeliveryFees(){
        return delivery_fees;
    } 
    
    public void setDeliveryFees(float deliveryFees){
        this.delivery_fees = deliveryFees;
    }

    public Set<Orders> getOrder(){
        return order;
    }
    public void setOrder(Set<Orders> order){
        this.order = order;
    }
    
}

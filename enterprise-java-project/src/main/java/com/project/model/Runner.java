package com.project.model;

import com.project.enums.RunnerStatus;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float delivery_fees;
    public RunnerStatus status;
    @OneToOne(mappedBy = "runner")
    private Orders order;

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

    public Orders getOrder(){
        return order;
    }
    public void setOrder(Orders order){
        this.order = order;
    }
    
}

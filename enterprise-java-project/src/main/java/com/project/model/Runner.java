package com.project.model;

import com.project.enums.RunnerStatusEnum;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private double deliveryFees;
    @NotNull
    private RunnerStatusEnum status;
    @OneToOne(optional = false)
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "runner")
    private Set<Orders> orders;

    public Runner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RunnerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RunnerStatusEnum status) {
        this.status = status;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
}

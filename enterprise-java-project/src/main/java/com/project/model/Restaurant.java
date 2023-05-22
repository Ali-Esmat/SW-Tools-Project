package com.project.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToOne(optional = false)
    @JoinColumn(name = "ownerId")
    private RestaurantOwner owner;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<Meal> menu;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<Orders> orders;

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public RestaurantOwner getOwner() {
        return owner;
    }

    public void setOwner(RestaurantOwner owner) {
        this.owner = owner;
    }

    public Set<Meal> getMenu() {
        return menu;
    }

    public void setMenu(Set<Meal> menu) {
        this.menu = menu;
    }
}

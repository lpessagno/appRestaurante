package com.example.restaurantmodel.model;

import java.io.Serializable;

/**
 * Created by Jorge on 7/24/2016.
 */
public class Menu implements Serializable {

    private int id;
    private String name;
    private double price;
    private Restaurant restaurant;

    public Menu() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

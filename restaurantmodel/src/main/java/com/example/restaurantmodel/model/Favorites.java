package com.example.restaurantmodel.model;

/**
 * Created by azapata on 04/08/2016.
 */
public class Favorites {


    private int id;
    private User user;
    private Restaurant restaurant;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

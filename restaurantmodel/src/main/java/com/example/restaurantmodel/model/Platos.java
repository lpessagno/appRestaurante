package com.example.restaurantmodel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jorge on 7/24/2016. USER PHOTOS
 */
public class Platos implements Serializable {

    private int id;
    private User user;
    private Restaurant restaurant;
    private String description;
    private Date date;

    //Usar la mas eficiente
    private byte[] photo;

    public Platos() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

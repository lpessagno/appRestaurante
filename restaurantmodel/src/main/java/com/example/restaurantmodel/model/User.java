package com.example.restaurantmodel.model;

import java.io.Serializable;

/**
 * Created by Jorge on 7/24/2016.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;

    private Restaurant[] favorites;
    private Commentary[] commentaries;
    private Platos[] userPhotos;
    private DefaultSearch search;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Restaurant[] getFavorites() {
        return favorites;
    }

    public void setFavorites(Restaurant[] favorites) {
        this.favorites = favorites;
    }

    public Commentary[] getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(Commentary[] commentaries) {
        this.commentaries = commentaries;
    }

    public Platos[] getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(Platos[] userPhotos) {
        this.userPhotos = userPhotos;
    }

}

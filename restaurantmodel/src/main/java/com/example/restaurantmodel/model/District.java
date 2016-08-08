package com.example.restaurantmodel.model;

import java.io.Serializable;

/**
 * Created by Jorge on 7/24/2016.
 */
public class District implements Serializable {

    private int id;
    private String name;

    public District() {}

    public District(int iddist) {
        setId(iddist);
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
}

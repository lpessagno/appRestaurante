package com.example.restaurantmodel.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 7/24/2016.
 */
public class DefaultSearch implements Serializable {

    private int id;

    private String name;
    private District district;
    private int ranking;
    private double price_low;
    private double price_high;
    private List<Category> categories;

    public DefaultSearch() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice_low() {
        return price_low;
    }

    public void setPrice_low(double price_low) {
        this.price_low = price_low;
    }

    public double getPrice_high() {
        return price_high;
    }

    public void setPrice_high(double price_high) {
        this.price_high = price_high;
    }
}

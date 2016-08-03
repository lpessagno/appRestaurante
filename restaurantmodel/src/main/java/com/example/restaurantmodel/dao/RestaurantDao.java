package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.Restaurant;

import java.util.List;

/**
 * Created by Jorge on 7/24/2016.
 */
public interface RestaurantDao {

    long insert(Restaurant rest);
    long update(Restaurant rest);
    long delete();
    Restaurant get(int id);
    List<Restaurant> list();

}

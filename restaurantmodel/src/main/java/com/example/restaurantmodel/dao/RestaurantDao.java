package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.Restaurant;

import java.util.List;

/**
 * Created by Jorge on 7/24/2016.
 */
public interface RestaurantDao {

    public long insert(Restaurant restaurant);
    public long update(Restaurant restaurant);
    public long delete(Restaurant restaurant);
    public Restaurant get(int id);
    public List<Restaurant> list();
    //public List<Category> getRestaurantCategories(int restaurantid);

}

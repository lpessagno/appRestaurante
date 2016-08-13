package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.Platos;

import java.util.List;

/**
 * Created by Jorge on 8/8/2016.
 */
public interface PlatosDao {

    long insertPlato(Platos plato);
    Platos get(int id);
    List<Platos> listByRestaurantId(int restid);
    List<Platos> listByUserId(int userid);

}

package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.User;

/**
 * Created by Jorge on 7/24/2016.
 */
public interface UserDao {

    long insert(User user);
    long update(User user);
    User getByName(String Name);
    User get(int id);

}

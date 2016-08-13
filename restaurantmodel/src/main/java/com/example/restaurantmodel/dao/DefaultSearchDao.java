package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.DefaultSearch;
import com.example.restaurantmodel.model.Restaurant;

import java.util.List;

/**
 * Created by Jorge on 8/12/2016.
 */
public interface DefaultSearchDao {

    public long insert(DefaultSearch search);
    public int update(DefaultSearch search);
    public DefaultSearch get(int id);
}

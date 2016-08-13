package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Menu;

import java.util.List;

/**
 * Created by Jorge on 7/24/2016.
 */
public interface CategoryDistrictDao {

    //long insert();
    //long update();
    //long delete();

    Category getCategory(int id);
    List<Category> listCategory();
    District getDistrict(int id);
    List<District> listDistrict();

    void insertSearchCategory(int id,List<Category> list);
    int deleteSearchCategoryFromSearchId(int searchId);

}

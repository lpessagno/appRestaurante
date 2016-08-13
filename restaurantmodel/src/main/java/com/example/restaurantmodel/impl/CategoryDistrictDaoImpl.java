package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.CategoryDistrictDao;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.District;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/7/2016.
 */
public class CategoryDistrictDaoImpl implements CategoryDistrictDao {

    Context context;

    public CategoryDistrictDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public Category getCategory(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Categories._ID+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Categories.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        Category cat = new Category();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            cat.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Categories._ID)));
            cat.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Categories.COLUMN_NAME)));
        } else {
            cat = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return cat;
    }

    @Override
    public List<Category> listCategory() {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Categories.TABLE_NAME,null,null,null,null,null,null);
        List<Category> list = new ArrayList<Category>();
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Categories._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Categories.COLUMN_NAME)));
                list.add(category);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    @Override
    public District getDistrict(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.District._ID+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.District.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        District district = new District();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            district.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.District._ID)));
            district.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.District.COLUMN_NAME)));
        } else {
            district = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return district;
    }

    @Override
    public List<District> listDistrict() {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.District.TABLE_NAME,null,null,null,null,null,null);
        List<District> list = new ArrayList<District>();
        if (cursor.moveToFirst()) {
            do {
                District dist = new District();
                dist.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.District._ID)));
                dist.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.District.COLUMN_NAME)));
                list.add(dist);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    @Override
    public void insertSearchCategory(int id, List<Category> list) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        for(Category cat:list){
            ContentValues content = new ContentValues();
            content.put(RestaurantSchemaContract.Search_Categories.COLUMN_SEARCH,id);
            content.put(RestaurantSchemaContract.Search_Categories.COLUMN_CATEGORY,cat.getId());

            sqlite.insert(RestaurantSchemaContract.Search_Categories.TABLE_NAME,null,content);
        }
        sqlite.close();
    }

    @Override
    public int deleteSearchCategoryFromSearchId(int searchId) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        String whereClause = RestaurantSchemaContract.Search_Categories.COLUMN_SEARCH+"=?";
        String[] whereArgs = new String[]{""+searchId};
        int num = sqlite.delete(RestaurantSchemaContract.Search_Categories.TABLE_NAME,whereClause,whereArgs);

        sqlite.close();

        return num;
    }

}

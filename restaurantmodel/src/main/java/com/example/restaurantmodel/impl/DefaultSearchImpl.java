package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.CategoryDistrictDao;
import com.example.restaurantmodel.dao.DefaultSearchDao;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.DefaultSearch;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/12/2016.
 */
public class DefaultSearchImpl implements DefaultSearchDao {
    Context context;

    public DefaultSearchImpl(Context context) {
        this.context = context;
    }

    @Override
    public long insert(DefaultSearch search) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_NAME,search.getName());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_DISTRICT,search.getDistrict().getId());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_RANKING,search.getRanking());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_LOW,search.getPrice_low());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_HIGH,search.getPrice_high());

        long id = sqlite.insert(RestaurantSchemaContract.DefaultSearch.TABLE_NAME,null,content);
        int searchId = (int) id;
        Log.d("DEFAULTID","defId: "+id);

        sqlite.close();
        CategoryDistrictDao dao = new CategoryDistrictDaoImpl(context);
        dao.insertSearchCategory(searchId,search.getCategories());

        return id;
    }


    @Override
    public int update(DefaultSearch search) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_NAME,search.getName());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_DISTRICT,search.getDistrict().getId());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_RANKING,search.getRanking());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_LOW,search.getPrice_low());
        content.put(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_HIGH,search.getPrice_high());

        String whereClause = RestaurantSchemaContract.DefaultSearch._ID+" = ?";
        String[] args = {""+search.getId()};

        int id = sqlite.update(RestaurantSchemaContract.DefaultSearch.TABLE_NAME,content,whereClause,args);
        sqlite.close();

        CategoryDistrictDao dao = new CategoryDistrictDaoImpl(context);
        dao.deleteSearchCategoryFromSearchId(search.getId());
        dao.insertSearchCategory(search.getId(),search.getCategories());

        return id;
    }

    @Override
    public DefaultSearch get(int id) {
        DefaultSearch search = getDefaultSearch(id);
        //getDistrict
        search.setDistrict(getSearchDistrict(search.getDistrict().getId()));
        //getCategories
        search.setCategories(getSearchCategories(search.getId()));
        return search;
    }

    private DefaultSearch getDefaultSearch(int id){
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        String whereClause = RestaurantSchemaContract.DefaultSearch._ID+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.DefaultSearch.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        DefaultSearch search = new DefaultSearch();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            search.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch._ID)));
            search.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch.COLUMN_NAME)));
            District d = new District();
            d.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch.COLUMN_DISTRICT)));
            search.setDistrict(d);
            search.setRanking(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch.COLUMN_RANKING)));
            search.setPrice_low(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_LOW)));
            search.setPrice_high(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_HIGH)));
        } else {
            search = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return search;
    }

    private List<Category> getSearchCategories(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        String whereClause = RestaurantSchemaContract.Search_Categories.COLUMN_SEARCH+"=?";
        String[] whereArgs = new String[]{""+id};
        List<Integer> cat_list = new ArrayList<Integer>();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Search_Categories.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                cat_list.add(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Search_Categories.COLUMN_CATEGORY)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        List<Category> list = new ArrayList<Category>();
        CategoryDistrictDao catDao = new CategoryDistrictDaoImpl(context);
        for (Integer catid:cat_list){
            Category cat = catDao.getCategory(catid);
            list.add(cat);
        }

        return list;
    }

    private District getSearchDistrict(int id) {
        CategoryDistrictDao dao = new CategoryDistrictDaoImpl(context);
        return dao.getDistrict(id);
    }
}

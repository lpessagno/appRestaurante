package com.example.restaurantmodel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/2/2016.
 */
public class RestaurantDaoImpl implements RestaurantDao {

    Context context;

    public RestaurantDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public long insert(Restaurant rest) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,rest.getName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,rest.getHorario());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,rest.getEmail());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,rest.getPhone());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_MENU,rest.getMenu().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,rest.getDistrict().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,rest.getAddress());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,rest.getLatitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,rest.getLongitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,rest.getPhotoid());

        long id = sqlite.insert(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,content);

        //insert other restaurant properties like category

        //importante cerrar la conexion
        sqlite.close();

        return id;
    }

    @Override
    public long update(Restaurant rest) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,rest.getName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,rest.getHorario());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,rest.getEmail());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,rest.getPhone());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_MENU,rest.getMenu().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,rest.getDistrict().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,rest.getAddress());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,rest.getLatitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,rest.getLongitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,rest.getPhotoid());

        String whereClause = RestaurantSchemaContract.Restaurant._ID+" = ?";
        String[] args = {""+rest.getId()};

        long id = sqlite.update(RestaurantSchemaContract.Restaurant.TABLE_NAME,content,whereClause,args);

        return id;
    }

    @Override
    public long delete() {
        return 0;
    }

    @Override
    public Restaurant get(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,null,null,null,null,null);
        Restaurant rest = new Restaurant();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant._ID)));
            rest.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_NAME)));
            rest.setHorario(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO)));
            rest.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL)));
            rest.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHONE)));
            //MENU
            rest.setAvg_ranking(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_RANKING)));
            rest.setAvg_price(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE)));
            //DISTRICT
            rest.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS)));
            rest.setLatitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE)));
            rest.setLongitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE)));
            rest.setPhotoid(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO)));

        } else {
            rest = null;
        }
        return rest;
    }

    @Override
    public List<Restaurant> list() {

        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,null,null,null,null,null);
        List<Restaurant> list = new ArrayList<Restaurant>();
        if (cursor.moveToFirst()) {
            do {
                Restaurant rest = new Restaurant();
                rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant._ID)));
                rest.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_NAME)));
                rest.setHorario(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO)));
                rest.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL)));
                rest.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHONE)));
                //MENU
                rest.setAvg_ranking(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_RANKING)));
                rest.setAvg_price(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE)));
                //DISTRICT
                rest.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS)));
                rest.setLatitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE)));
                rest.setLongitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE)));
                rest.setPhotoid(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO)));

                list.add(rest);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }
}

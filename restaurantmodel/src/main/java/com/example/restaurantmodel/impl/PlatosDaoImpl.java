package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.PlatosDao;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Platos;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jorge on 8/8/2016.
 */
public class PlatosDaoImpl implements PlatosDao {

    Context context;

    public PlatosDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public long insertPlato(Platos plato) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_USER,plato.getUser().getId());
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT,plato.getRestaurant().getId());
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_USER,plato.getUser().getId());
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_NAME,plato.getDescription());
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_DISH,plato.getPhoto());
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_DATE,plato.getDate().getTime());
        long id = sqlite.insert(RestaurantSchemaContract.UserPhotos.TABLE_NAME,null,content);
        sqlite.close();

        return 0;
    }

    @Override
    public Platos get(int dishid) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.UserPhotos._ID+"=?";
        String[] whereArgs = new String[]{""+dishid};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.UserPhotos.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        Platos plato = new Platos();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            plato.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos._ID)));
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_USER)));
            plato.setUser(user);
            Restaurant rest = new Restaurant();
            rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT)));
            plato.setRestaurant(rest);
            plato.setDescription(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_NAME)));
            Date date = new Date(cursor.getLong(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DATE)));
            plato.setDate(date);
            plato.setPhoto(cursor.getBlob(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DISH)));
        } else {
            plato = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return plato;
    }

    @Override
    public List<Platos> listByRestaurantId(int restid) {
        AppRestSqlOpenHelper db = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite=db.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT+"=?";
        String[] whereArgs = new String[]{""+restid};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.UserPhotos.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        List<Platos> list = new ArrayList<Platos>();
        if (cursor.moveToFirst()) {
            do {
                Platos dish = new Platos();
                dish.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos._ID)));
                int userId = cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_USER));
                User user = new User();
                user.setId(userId);
                dish.setUser(user);
                Restaurant rest = new Restaurant();
                rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT)));
                dish.setDescription(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_NAME)));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DATE)));
                dish.setDate(date);
                dish.setPhoto(cursor.getBlob(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DISH)));
                list.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    @Override
    public List<Platos> listByUserId(int userid) {
        AppRestSqlOpenHelper db = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite=db.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.UserPhotos.COLUMN_USER+"=?";
        String[] whereArgs = new String[]{""+userid};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.UserPhotos.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        List<Platos> list = new ArrayList<Platos>();
        if (cursor.moveToFirst()) {
            do {
                Platos dish = new Platos();
                dish.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos._ID)));
                int userId = cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_USER));
                User user = new User();
                user.setId(userId);
                dish.setUser(user);
                Restaurant rest = new Restaurant();
                rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT)));
                dish.setDescription(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_NAME)));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DATE)));
                dish.setDate(date);
                dish.setPhoto(cursor.getBlob(cursor.getColumnIndex(RestaurantSchemaContract.UserPhotos.COLUMN_DISH)));
                list.add(dish);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

}

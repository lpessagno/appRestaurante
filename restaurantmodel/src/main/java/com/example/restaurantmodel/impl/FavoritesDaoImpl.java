package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.FavoritesDAO;
import com.example.restaurantmodel.model.DefaultSearch;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Favorites;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/15/2016.
 */
public class FavoritesDaoImpl implements FavoritesDAO {

    private Context context;

    public FavoritesDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public long insertarFavorites(Favorites favorites) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Favorites.COLUMN_USER,favorites.getUser().getId());
        content.put(RestaurantSchemaContract.Favorites.COLUMN_RESTAURANT,favorites.getRestaurant().getId());
        long id = sqlite.insert(RestaurantSchemaContract.Favorites.TABLE_NAME,null,content);
        sqlite.close();

        return id;
    }

    @Override
    public int eliminarFavorites(Favorites favorites) {
        return 0;
    }

    @Override
    public List<Favorites> listarFavorites(int userid) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Favorites.COLUMN_USER+"=?";
        String[] whereArgs = new String[]{""+userid};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Favorites.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        List<Favorites> list = new ArrayList<Favorites>();
        if (cursor.moveToFirst()) {
            do {
                Favorites fav = new Favorites();
                fav.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Favorites._ID)));
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Favorites.COLUMN_USER)));
                fav.setUser(user);
                Restaurant rest = new Restaurant();
                rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Favorites.COLUMN_RESTAURANT)));
                fav.setRestaurant(rest);

                list.add(fav);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    @Override
    public Favorites getExistFavorite(User user, Restaurant rest) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Favorites.COLUMN_USER+"=? AND "+RestaurantSchemaContract.Favorites.COLUMN_RESTAURANT+"=?";
        String[] whereArgs = new String[]{""+user.getId(),""+rest.getId()};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Favorites.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        Favorites fav = new Favorites();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            fav.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Favorites._ID)));
            fav.setUser(user);
            fav.setRestaurant(rest);
        } else {
            fav = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return fav;
    }
}

package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.DefaultSearchDao;
import com.example.restaurantmodel.dao.FavoritesDAO;
import com.example.restaurantmodel.dao.PlatosDao;
import com.example.restaurantmodel.dao.ReseniaDAO;
import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.DefaultSearch;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Favorites;
import com.example.restaurantmodel.model.Platos;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/7/2016.
 */
public class UserDaoImpl implements UserDao {

    Context context;

    public UserDaoImpl(Context context){
        this.context = context;
    }


    @Override
    public long insert(User user) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.User.COLUMN_NAME,user.getName());
        content.put(RestaurantSchemaContract.User.COLUMN_EMAIL,user.getEmail());
        content.put(RestaurantSchemaContract.User.COLUMN_PHONE,user.getPhone());
        content.put(RestaurantSchemaContract.User.COLUMN_PWD,user.getPassword());
        long id = sqlite.insert(RestaurantSchemaContract.User.TABLE_NAME,null,content);
        sqlite.close();

        return id;
    }

    @Override
    public long update(User user) {

        //SAVE DEFAULT_SEARCH?
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.User.COLUMN_NAME,user.getName());
        content.put(RestaurantSchemaContract.User.COLUMN_EMAIL,user.getEmail());
        content.put(RestaurantSchemaContract.User.COLUMN_PHONE,user.getPhone());
        content.put(RestaurantSchemaContract.User.COLUMN_PWD,user.getPassword());
        content.put(RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH,user.getSearch().getId());

        String whereClause = RestaurantSchemaContract.User._ID+" = ?";
        String[] args = {""+user.getId()};
        long id = sqlite.update(RestaurantSchemaContract.User.TABLE_NAME,content,whereClause,args);
        sqlite.close();

        return id;
    }

    @Override
    public User getByName(String name) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.User.COLUMN_NAME+"=?";
        String[] whereArgs = new String[]{""+name};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.User.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        User user = new User();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.User._ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_PHONE)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_PWD)));
            DefaultSearch ds = new DefaultSearch();
            ds.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH)));
        } else {
            user = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        //get other Values
        //getFavorites
        if (user!=null){
            //getDefaultSearch
            if(user.getSearch()!=null) {
                user.setSearch(getUserDefaultSearch(user.getSearch().getId()));
            }
            //getComment
            user.setCommentaries(getUserComments(user.getId()));
            //getPlatos
            user.setUserPhotos(getPhotosByUser(user.getId()));
            //getFavorites
            user.setFavorites(getFavorites(user.getId()));
        }

        return user;
    }



    @Override
    public User get(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.User._ID+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.User.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        User user = new User();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.User._ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_PHONE)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_PWD)));
            DefaultSearch ds = new DefaultSearch();
            ds.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH)));
        } else {
            user = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        //get other Values
        //Validar cuales son necesarios
        return user;
    }

    private List<Platos> getPhotosByUser(int id) {
        PlatosDao dao = new PlatosDaoImpl(context);
        return dao.listByUserId(id);
    }

    private List<Commentary> getUserComments(int id) {
        ReseniaDAO dao = new ReseniaDaoImpl(context);
        return dao.getCommentByUserId(id);
    }

    private DefaultSearch getUserDefaultSearch(int id) {
        DefaultSearchDao dao = new DefaultSearchImpl(context);
        return dao.get(id);
    }

    private List<Restaurant> getFavorites(int id) {
        FavoritesDAO dao = new FavoritesDaoImpl(context);
        List<Favorites> list_fav = dao.listarFavorites(id);
        RestaurantDao daorest = new RestaurantDaoImpl(context);
        List<Restaurant> list = new ArrayList<Restaurant>();

        for (Favorites fav:list_fav){
            list.add(daorest.get(fav.getRestaurant().getId()));
        }
        return list;
    }
}

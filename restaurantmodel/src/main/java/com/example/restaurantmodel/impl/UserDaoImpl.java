package com.example.restaurantmodel.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

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
        return 0;
    }

    @Override
    public long update(User user) {
        return 0;
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

        } else {
            user = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return user;
    }
}

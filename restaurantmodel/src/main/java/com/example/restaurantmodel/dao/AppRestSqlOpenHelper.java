package com.example.restaurantmodel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jorge on 7/24/2016.
 */
public class AppRestSqlOpenHelper extends SQLiteOpenHelper {

    private static final String dbName = "appRestaurantDB.db";
    private static final int versionDB = 1;


    public AppRestSqlOpenHelper(Context context) {
        super(context, dbName, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

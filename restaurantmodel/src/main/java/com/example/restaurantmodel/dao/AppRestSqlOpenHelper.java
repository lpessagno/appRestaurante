package com.example.restaurantmodel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;

/**
 * Created by Jorge on 7/24/2016.
 */
public class AppRestSqlOpenHelper extends SQLiteOpenHelper {

    private static final String dbName = "appRestaurantDB.db";
    private static final int versionDB = 1;
    private static final String NOTNULL = "NOT NULL";



    public AppRestSqlOpenHelper(Context context) {
        super(context, dbName, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_menu = "CREATE TABLE "+RestaurantSchemaContract.Menu.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Menu._ID+" INTEGER PRIMARY KEY, "+
                RestaurantSchemaContract.Menu.COLUMN_NAME+" TEXT "+NOTNULL+", "+
                RestaurantSchemaContract.Menu.COLUMN_PRICE+" NUMERIC"+
                ")";
        db.execSQL(sql_menu);

        String sql_district = "CREATE TABLE "+RestaurantSchemaContract.District.TABLE_NAME+" ( "+
                RestaurantSchemaContract.District._ID+" INTEGER PRIMARY KEY, "+
                RestaurantSchemaContract.District.COLUMN_NAME+" TEXT "+NOTNULL+" )";
        db.execSQL(sql_district);

        String sql_categories = "CREATE TABLE "+RestaurantSchemaContract.Categories.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Categories._ID+" INTEGER PRIMARY KEY, "+
                RestaurantSchemaContract.Categories.COLUMN_NAME+" TEXT "+NOTNULL+" )";
        db.execSQL(sql_categories);

        String sql_restautrant = "CREATE TABLE "+RestaurantSchemaContract.Restaurant.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Restaurant._ID+" INTEGER PRIMARY KEY, "+
                RestaurantSchemaContract.Restaurant.COLUMN_NAME+" TEXT "+NOTNULL+", "+
                RestaurantSchemaContract.Restaurant.COLUMN_HORARIO+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_EMAIL+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_PHONE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_MENU+" INTEGER , "+
                RestaurantSchemaContract.Restaurant.COLUMN_RANKING+" NUMERIC , "+
                RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE+" NUMERIC , "+
                RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT+" INTEGER , "+
                RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_PHOTO+" INTEGER, "+  // Puede cambiar si se decide utilizar byte[]-> CLOB, string filesystem -> TEXT
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant.COLUMN_MENU+") REFERENCES "+RestaurantSchemaContract.Menu.TABLE_NAME+"("+RestaurantSchemaContract.Menu._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT+") REFERENCES "+RestaurantSchemaContract.District.TABLE_NAME+"("+RestaurantSchemaContract.District._ID+")"+
                ")";
        db.execSQL(sql_restautrant);

        String sql_rest_cat = "CREATE TABLE "+RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Restaurant_Categories._ID+" INTEGER PRIMARY KEY, "+
                RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT+" INTEGER , "+
                RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES+" INTEGER , "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES+") REFERENCES "+RestaurantSchemaContract.Categories.TABLE_NAME+"("+RestaurantSchemaContract.Categories._ID+")"+
                ")";
        db.execSQL(sql_rest_cat);

        //@
        String sql_resenia = "CREATE TABLE "+RestaurantSchemaContract.Resenia.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Resenia._ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+
                RestaurantSchemaContract.Resenia.COLUMN_USER +" INTEGER ," +
                RestaurantSchemaContract.Resenia.COLUMN_RESTAURANT +" INTEGER ," +
                RestaurantSchemaContract.Resenia.COLUMN_RANKING +" INTEGER NOT NULL," +
                RestaurantSchemaContract.Resenia.COLUMN_PRECIO +" INTEGER NOT NULL," +
                RestaurantSchemaContract.Resenia.COLUMN_COMENTARIO +" TEXT NOT NULL," +
                RestaurantSchemaContract.Resenia.COLUMN_IMAGEN +"TEXT NOT NULL);";
        db.execSQL(sql_resenia);


        String sql_favorites = "CREATE TABLE "+RestaurantSchemaContract.Favorites.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Resenia._ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+
                RestaurantSchemaContract.Resenia.COLUMN_USER +" INTEGER ," +
                RestaurantSchemaContract.Resenia.COLUMN_RESTAURANT +" INTEGER);";
        db.execSQL(sql_favorites);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

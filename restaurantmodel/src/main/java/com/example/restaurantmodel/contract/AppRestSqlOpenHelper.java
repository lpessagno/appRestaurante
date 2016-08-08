package com.example.restaurantmodel.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        Log.d("RESTAURANTAPPDB","onCREATE");

        //DISTRICT
        String sql_district = "CREATE TABLE "+RestaurantSchemaContract.District.TABLE_NAME+" ( "+
                RestaurantSchemaContract.District._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.District.COLUMN_NAME+" TEXT "+NOTNULL+" )";
        db.execSQL(sql_district);

        //CATEGORY
        String sql_categories = "CREATE TABLE "+RestaurantSchemaContract.Categories.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Categories._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.Categories.COLUMN_NAME+" TEXT "+NOTNULL+" )";
        db.execSQL(sql_categories);

        //DEFAULT_SEARCH
        String sql_search = "CREATE TABLE "+RestaurantSchemaContract.DefaultSearch.TABLE_NAME+" ( "+
                RestaurantSchemaContract.DefaultSearch._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.DefaultSearch.COLUMN_NAME+" TEXT "+NOTNULL+" " +
                RestaurantSchemaContract.DefaultSearch.COLUMN_DISTRICT+" INTEGER, "+
                RestaurantSchemaContract.DefaultSearch.COLUMN_RANKING+" INTEGER, "+
                RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_LOW+" NUMERIC, "+
                RestaurantSchemaContract.DefaultSearch.COLUMN_PRICE_HIGH+" NUMERIC, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.DefaultSearch.COLUMN_DISTRICT+") REFERENCES "+RestaurantSchemaContract.District.TABLE_NAME+"("+RestaurantSchemaContract.District._ID+")"+
                ")";
        db.execSQL(sql_search);

        //RESTAURANT
        String sql_restautrant = "CREATE TABLE "+RestaurantSchemaContract.Restaurant.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Restaurant._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_NAME+" TEXT "+NOTNULL+", "+
                RestaurantSchemaContract.Restaurant.COLUMN_HORARIO+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_EMAIL+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_PHONE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_RANKING+" NUMERIC , "+
                RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE+" NUMERIC , "+
                RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT+" INTEGER , "+
                RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE+" TEXT, "+
                RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID+" INTEGER, "+
                RestaurantSchemaContract.Restaurant.COLUMN_PHOTO+" BLOB, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT+") REFERENCES "+RestaurantSchemaContract.District.TABLE_NAME+"("+RestaurantSchemaContract.District._ID+")"+
                ")";
        db.execSQL(sql_restautrant);

        //MENU
        String sql_menu = "CREATE TABLE "+RestaurantSchemaContract.Menu.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Menu._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.Menu.COLUMN_NAME+" TEXT "+NOTNULL+", "+
                RestaurantSchemaContract.Menu.COLUMN_PRICE+" NUMERIC, "+
                RestaurantSchemaContract.Menu.COLUMN_RESTAURANT+" INTEGER, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Menu.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        db.execSQL(sql_menu);

        //USER
        String sql_user = "CREATE TABLE "+RestaurantSchemaContract.User.TABLE_NAME+" ( "+
                RestaurantSchemaContract.User._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.User.COLUMN_NAME+" TEXT "+NOTNULL+", "+
                RestaurantSchemaContract.User.COLUMN_EMAIL+" TEXT, "+
                RestaurantSchemaContract.User.COLUMN_PHONE+" TEXT, "+
                RestaurantSchemaContract.User.COLUMN_PWD+" TEXT, "+
                RestaurantSchemaContract.User.COLUMN_AVATAR+" BLOB, "+
                RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH+" INTEGER, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH+") REFERENCES "+RestaurantSchemaContract.DefaultSearch.TABLE_NAME+"("+RestaurantSchemaContract.DefaultSearch._ID+"),"+
                ")";
        db.execSQL(sql_user);

        //RESTAURANT_CATEGORY
        String sql_rest_cat = "CREATE TABLE "+RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Restaurant_Categories._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT+" INTEGER , "+
                RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES+" INTEGER , "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES+") REFERENCES "+RestaurantSchemaContract.Categories.TABLE_NAME+"("+RestaurantSchemaContract.Categories._ID+")"+
                ")";
        db.execSQL(sql_rest_cat);

        //COMMENT
        String sql_resenia = "CREATE TABLE "+ RestaurantSchemaContract.Comment.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Comment._ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+
                RestaurantSchemaContract.Comment.COLUMN_USER +" INTEGER NOT NULL," +
                RestaurantSchemaContract.Comment.COLUMN_RESTAURANT +" INTEGER NOT NULL," +
                RestaurantSchemaContract.Comment.COLUMN_RANKING +" INTEGER NOT NULL," +
                RestaurantSchemaContract.Comment.COLUMN_PRICE +" NUMERIC NOT NULL," +
                RestaurantSchemaContract.Comment.COLUMN_COMMENT +" TEXT NOT NULL," +
                RestaurantSchemaContract.Comment.COLUMN_DATE+"DATE NOT NULL, "+
                RestaurantSchemaContract.Comment.COLUMN_IMAGEN +"TEXT NOT NULL), "+ //que es esto
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_USER+") REFERENCES "+RestaurantSchemaContract.User.TABLE_NAME+"("+RestaurantSchemaContract.User._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        db.execSQL(sql_resenia);

        //FAVORITES
        String sql_favorites = "CREATE TABLE "+RestaurantSchemaContract.Favorites.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Comment._ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+
                RestaurantSchemaContract.Comment.COLUMN_USER +" INTEGER ," +
                RestaurantSchemaContract.Comment.COLUMN_RESTAURANT +" INTEGER), "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_USER+") REFERENCES "+RestaurantSchemaContract.User.TABLE_NAME+"("+RestaurantSchemaContract.User._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        db.execSQL(sql_favorites);

        //CATEGORY_SEARCH
        String sql_cat_search = "CREATE TABLE "+RestaurantSchemaContract.Search_Categories.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Search_Categories._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.Search_Categories.COLUMN_SEARCH+" INTEGER , "+
                RestaurantSchemaContract.Search_Categories.COLUMN_CATEGORY+" INTEGER , "+
                "FOREIGN KEY ("+RestaurantSchemaContract.Search_Categories.COLUMN_SEARCH+") REFERENCES "+RestaurantSchemaContract.DefaultSearch.TABLE_NAME+"("+RestaurantSchemaContract.DefaultSearch._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Search_Categories.COLUMN_CATEGORY+") REFERENCES "+RestaurantSchemaContract.Categories.TABLE_NAME+"("+RestaurantSchemaContract.Categories._ID+")"+
                ")";
        db.execSQL(sql_cat_search);

        //PLATOS o USER PHOTOS
        String sql_platos = "CREATE TABLE "+ RestaurantSchemaContract.UserPhotos.TABLE_NAME+" ( "+
                RestaurantSchemaContract.UserPhotos._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RestaurantSchemaContract.UserPhotos.COLUMN_USER +" INTEGER NOT NULL," +
                RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT +" INTEGER NOT NULL," +
                RestaurantSchemaContract.UserPhotos.COLUMN_NAME +" TEXT, "+
                RestaurantSchemaContract.UserPhotos.COLUMN_DISH+" BLOB, "+
                RestaurantSchemaContract.UserPhotos.COLUMN_DATE+"DATE NOT NULL, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.UserPhotos.COLUMN_USER+") REFERENCES "+RestaurantSchemaContract.User.TABLE_NAME+"("+RestaurantSchemaContract.User._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        db.execSQL(sql_resenia);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

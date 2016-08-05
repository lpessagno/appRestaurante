package com.example.restaurantmodel.contract;

import android.provider.BaseColumns;

/**
 * Created by Jorge on 7/31/2016.
 */
public class RestaurantSchemaContract {

    public RestaurantSchemaContract() {}

    public static abstract class Categories implements BaseColumns {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME = "name";
    }

    public static abstract class District implements BaseColumns {
        public static final String TABLE_NAME = "district";
        public static final String COLUMN_NAME = "name";
    }

    public static abstract class Menu implements BaseColumns {
        public static final String TABLE_NAME = "menu";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
    }

    public static abstract class Restaurant implements BaseColumns {
        public static final String TABLE_NAME = "restaurant";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_HORARIO = "horario";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_MENU = "menuid";
        public static final String COLUMN_RANKING = "avg_ranking";
        public static final String COLUMN_AVG_PRICE = "avg_price";
        public static final String COLUMN_DISTRICT = "districtid";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_PHOTO = "photoid";  // id, string, CLOB
    }

    public static abstract class Restaurant_Categories implements BaseColumns {
        public static final String TABLE_NAME = "restaurant_categories";
        public static final String COLUMN_RESTAURANT = "idrestaurant";
        public static final String COLUMN_CATEGORIES = "idcategory";
    }


    public static final class Resenia implements BaseColumns{
        public static final String TABLE_NAME = "resenia";
        public static final String COLUMN_USER = "iduser";
        public static final String COLUMN_RESTAURANT = "idrestaurant";
        public static final String COLUMN_RANKING = "ranking";
        public static final String COLUMN_PRECIO = "price";
        public static final String COLUMN_COMENTARIO = "comentario";
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_IMAGEN = "imagen";
    }

    public static abstract class Favorites implements BaseColumns {
            public static final String TABLE_NAME = "favorites";
            public static final String COLUMN_NAME = "iduser";
            public static final String COLUMN_PRICE = "idrestaurant";
        }




}

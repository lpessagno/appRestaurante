package com.example.restaurantmodel.contract;

import android.provider.BaseColumns;

/**
 * Created by Jorge on 7/31/2016.
 */
public class RestaurantSchemaContract {

    public RestaurantSchemaContract() {
    }

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
        public static final String COLUMN_PHOTO_ID = "photoid";  // id for resources
        public static final String COLUMN_PHOTO = "photo"; // byte[] for imagenes
    }

    public static abstract class Restaurant_Categories implements BaseColumns {
        public static final String TABLE_NAME = "restaurant_categories";
        public static final String COLUMN_RESTAURANT = "idrestaurant";
        public static final String COLUMN_CATEGORIES = "idcategory";
    }

    public static abstract class Favorites implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_NAME = "iduser";
        public static final String COLUMN_PRICE = "idrestaurant";
    }

    public static abstract class DefaultSearch implements BaseColumns {
        public static final String TABLE_NAME = "default_search";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DISTRICT = "district_id";
        public static final String COLUMN_RANKING_ = "ranking";
        public static final String COLUMN_PRICE_LOW = "low_price";
        public static final String COLUMN_PRICE_HIGH = "high_price";
    }

    public static abstract class Search_Categories implements BaseColumns {
        public static final String TABLE_NAME = "search_categories";
        public static final String COLUMN_SEARCH = "default_search_id";
        public static final String COLUMN_CATEGORY = "idcategory";
    }

    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_PWD = "password";
        public static final String COLUMN_AVATAR = "avatar"; //BLOB
        public static final String COLUMN_DEFAULT_SEARCH = "default_search_id";
    }

    //COMMENTARIES
    public static final class Comment implements BaseColumns {
        public static final String TABLE_NAME = "commentaries";
        //        public static final String COLUMN_ID = "idresenia"; // ya existe
        public static final String COLUMN_USER = "iduser";
        public static final String COLUMN_RESTAURANT = "idrestaurant";
        public static final String COLUMN_RANKING = "ranking";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_IMAGEN = "imagen"; // cual imagen?
    }

    //PLATOS_FOTO
    public static final class UserPhotos implements BaseColumns {
        public static final String TABLE_NAME = "user_photos";
        public static final String COLUMN_USER = "iduser";
        public static final String COLUMN_RESTAURANT = "idrestaurant";
        public static final String COLUMN_NAME = "description";
        public static final String COLUMN_DISH = "dish_photo"; // BLOB
        public static final String COLUMN_DATE = "date";
    }





}

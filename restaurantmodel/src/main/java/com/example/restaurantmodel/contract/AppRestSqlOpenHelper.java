package com.example.restaurantmodel.contract;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.restaurantmodel.R;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by Jorge on 7/24/2016.
 */
public class AppRestSqlOpenHelper extends SQLiteOpenHelper {

    private static final String dbName = "RestaurantDB2.db";
    private static final int versionDB = 12;
    private static final String NOTNULL = "NOT NULL";
    private Context ctx;

    public AppRestSqlOpenHelper(Context context) {
        super(context, dbName, null, versionDB);
        ctx = context;
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
                RestaurantSchemaContract.DefaultSearch.COLUMN_NAME+" TEXT "+NOTNULL+", " +
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
                RestaurantSchemaContract.Restaurant.COLUMN_VOTE+" INTEGER , "+
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
                //RestaurantSchemaContract.User.COLUMN_AVATAR+" BLOB, "+
                RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH+" INTEGER, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.User.COLUMN_DEFAULT_SEARCH+") REFERENCES "+RestaurantSchemaContract.DefaultSearch.TABLE_NAME+"("+RestaurantSchemaContract.DefaultSearch._ID+")"+
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
                RestaurantSchemaContract.Comment.COLUMN_DATE+" DATE NOT NULL, "+
                RestaurantSchemaContract.Comment.COLUMN_IMAGEN +"TEXT, "+ //que es esto
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_USER+") REFERENCES "+RestaurantSchemaContract.User.TABLE_NAME+"("+RestaurantSchemaContract.User._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.Comment.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        db.execSQL(sql_resenia);

        //FAVORITES
        String sql_favorites = "CREATE TABLE "+RestaurantSchemaContract.Favorites.TABLE_NAME+" ( "+
                RestaurantSchemaContract.Comment._ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+
                RestaurantSchemaContract.Comment.COLUMN_USER +" INTEGER ," +
                RestaurantSchemaContract.Comment.COLUMN_RESTAURANT +" INTEGER, "+
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
                RestaurantSchemaContract.UserPhotos.COLUMN_DATE+" DATE NOT NULL, "+
                "FOREIGN KEY ("+RestaurantSchemaContract.UserPhotos.COLUMN_USER+") REFERENCES "+RestaurantSchemaContract.User.TABLE_NAME+"("+RestaurantSchemaContract.User._ID+"),"+
                "FOREIGN KEY ("+RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT+") REFERENCES "+RestaurantSchemaContract.Restaurant.TABLE_NAME+"("+RestaurantSchemaContract.Restaurant._ID+")"+
                ")";
        Log.d("ONCREATE",sql_platos);
        db.execSQL(sql_platos);

        /**********INSERTS*************/
        //DISTRICT
        insertColumn(db,RestaurantSchemaContract.District.TABLE_NAME,RestaurantSchemaContract.District.COLUMN_NAME,"Surquillo");
        insertColumn(db,RestaurantSchemaContract.District.TABLE_NAME,RestaurantSchemaContract.District.COLUMN_NAME,"Ate");
        insertColumn(db,RestaurantSchemaContract.District.TABLE_NAME,RestaurantSchemaContract.District.COLUMN_NAME,"Lima");
        insertColumn(db,RestaurantSchemaContract.District.TABLE_NAME,RestaurantSchemaContract.District.COLUMN_NAME,"La Molina");

        //Category
        insertColumn(db,RestaurantSchemaContract.Categories.TABLE_NAME,RestaurantSchemaContract.Categories.COLUMN_NAME,"Carnes");
        insertColumn(db,RestaurantSchemaContract.Categories.TABLE_NAME,RestaurantSchemaContract.Categories.COLUMN_NAME,"Italiano");
        insertColumn(db,RestaurantSchemaContract.Categories.TABLE_NAME,RestaurantSchemaContract.Categories.COLUMN_NAME,"Chino");



        int idrest1 = insertRestaurant(db);
        int idrest2 = insertRestaurant2(db);

        insertMenu(db,idrest1,"Parrilla de Carne",30.00);
        insertMenu(db,idrest1,"Arroz con Pollo",25.00);
        insertMenu(db,idrest1,"Arroz con Pato",35.00);
        insertMenu(db,idrest1,"Lomito Saltado",24.50);
        insertMenu(db,idrest1,"Hamburguesa",10.00);

        insertMenu(db,idrest2,"Sushi de Cangrejo",15.00);
        insertMenu(db,idrest2,"Arroz Chaufa",12.50);
        insertMenu(db,idrest2,"Sashimi",8.00);
        insertMenu(db,idrest2,"Combinado de Sushi",40.00);

        int user1 = insertUser1(db);
        int user2 = insertUser2(db);

        insertComment(db,idrest1,user1,"Muy Recomendado. Excelente",5,30.00);
        insertComment(db,idrest1,user2,"Me encanto mucho la Carne",4,25.00);
        insertComment(db,idrest2,user2,"Es muy bueno el Restaurant y tiene buena comida",4,45.00);

        insertPlato(db,idrest1,user1,"Lomo Saltado",ctx.getResources().getIdentifier("lomito","mipmap",ctx.getPackageName()));
        insertPlato(db,idrest1,user2,"Causa Rellena",ctx.getResources().getIdentifier("causa","mipmap",ctx.getPackageName()));
        insertPlato(db,idrest2,user2,"Causa de Atun",ctx.getResources().getIdentifier("causa","mipmap",ctx.getPackageName()));

       // db.close();
    }

    private void insertPlato(SQLiteDatabase db, int idrest, int user, String plato,int imgId) {
        Log.d("PLATOSINSERT","rest: "+idrest+", user: "+user+", imgid: "+imgId);
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_USER,user);
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_RESTAURANT,idrest);
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_NAME,plato);

        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),imgId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_DISH,byteArray);
        content.put(RestaurantSchemaContract.UserPhotos.COLUMN_DATE,(new Date().getTime())); //
        db.insert(RestaurantSchemaContract.UserPhotos.TABLE_NAME,null,content);
    }

    private void insertComment(SQLiteDatabase db, int idrest, int user, String comment, int rank, double price) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Comment.COLUMN_USER,user);
        content.put(RestaurantSchemaContract.Comment.COLUMN_RESTAURANT,idrest);
        content.put(RestaurantSchemaContract.Comment.COLUMN_RANKING,rank);
        content.put(RestaurantSchemaContract.Comment.COLUMN_PRICE,price);
        content.put(RestaurantSchemaContract.Comment.COLUMN_COMMENT,comment);
        Date date = new Date();
        Log.d("INSERTDATE",date.toString());
        content.put(RestaurantSchemaContract.Comment.COLUMN_DATE,date.getTime()); //VALIDAR
        db.insert(RestaurantSchemaContract.Comment.TABLE_NAME,null,content);
    }

    private int insertUser1(SQLiteDatabase db) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.User.COLUMN_NAME,"Otto Lorenz");
        content.put(RestaurantSchemaContract.User.COLUMN_EMAIL,"olorenz@gmail.com");
        content.put(RestaurantSchemaContract.User.COLUMN_PHONE,"555485456");
        content.put(RestaurantSchemaContract.User.COLUMN_PWD,"olorenz");
        int id = (int)db.insert(RestaurantSchemaContract.User.TABLE_NAME,null,content);
        return id;
    }

    private int insertUser2(SQLiteDatabase db) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.User.COLUMN_NAME,"Ringo Star");
        content.put(RestaurantSchemaContract.User.COLUMN_EMAIL,"rstar@beattles.com");
        content.put(RestaurantSchemaContract.User.COLUMN_PHONE,"777845987");
        content.put(RestaurantSchemaContract.User.COLUMN_PWD,"thebeattles");
        int id = (int)db.insert(RestaurantSchemaContract.User.TABLE_NAME,null,content);
        return id;
    }

    private void insertMenu(SQLiteDatabase db, int idrest, String name, double price) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Menu.COLUMN_NAME,name);
        content.put(RestaurantSchemaContract.Menu.COLUMN_PRICE,price);
        content.put(RestaurantSchemaContract.Menu.COLUMN_RESTAURANT,idrest);
        db.insert(RestaurantSchemaContract.Menu.TABLE_NAME,null,content);
    }

    private int insertRestaurant(SQLiteDatabase db) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,"labistecca");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,"8:00 - 18:00");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,"labistecca@mail.com");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,"(01) 5631641");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,4.5);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,27.50);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_VOTE,2);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,3);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,"calle 5");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,"-12.1066035");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,"-77.0372236");
        int imgid = ctx.getResources().getIdentifier("labistecca","drawable",ctx.getPackageName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID, imgid);
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),imgid);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,byteArray);
        db.insert(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,content);

        ContentValues catrest = new ContentValues();
        catrest.put(RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT,2);
        catrest.put(RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES,3);
        int id = (int)db.insert(RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME,null,catrest);
        return id;
    }

    private int insertRestaurant2(SQLiteDatabase db) {
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,"ebisu");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,"8:00 - 19:00");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,"ebisu@mail.com");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,"(043) 631641");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,4.0);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,45.00);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_VOTE,1);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,4);
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,"algun sitio");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,"-12.086522");
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,"-77.048332");
        int imgid = ctx.getResources().getIdentifier("ebisu","drawable",ctx.getPackageName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID, imgid);
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),imgid);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,byteArray);
        db.insert(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,content);

        ContentValues catrest = new ContentValues();
        catrest.put(RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT,1);
        catrest.put(RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES,1);
        int id = (int)db.insert(RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME,null,catrest);
        return id;
    }

    private void insertColumn(SQLiteDatabase db, String tableName,String coolumnName,String name) {
        ContentValues content = new ContentValues();
        content.put(coolumnName,name);
        db.insert(tableName,null,content);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("RESTAURANTAPPDB","onUpgrade");
        String sql = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        String sql1 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Comment.TABLE_NAME;
        sqLiteDatabase.execSQL(sql1);
        String sql3 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Favorites.TABLE_NAME;
        sqLiteDatabase.execSQL(sql3);
        String sql4 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Menu.TABLE_NAME;
        sqLiteDatabase.execSQL(sql4);
        String sql5 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Restaurant.TABLE_NAME;
        sqLiteDatabase.execSQL(sql5);
        String sql8 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Search_Categories.TABLE_NAME;
        sqLiteDatabase.execSQL(sql8);
        String sql6 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.Categories.TABLE_NAME;
        sqLiteDatabase.execSQL(sql6);
        String sql7 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.District.TABLE_NAME;
        sqLiteDatabase.execSQL(sql7);
        String sql10 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.UserPhotos.TABLE_NAME;
        sqLiteDatabase.execSQL(sql10);
        String sql9 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.User.TABLE_NAME;
        sqLiteDatabase.execSQL(sql9);
        String sql2 = "DROP TABLE IF EXISTS "+RestaurantSchemaContract.DefaultSearch.TABLE_NAME;
        sqLiteDatabase.execSQL(sql2);

        onCreate(sqLiteDatabase);
    }
}

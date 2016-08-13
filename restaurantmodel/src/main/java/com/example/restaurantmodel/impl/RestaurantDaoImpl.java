package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.CategoryDistrictDao;
import com.example.restaurantmodel.dao.PlatosDao;
import com.example.restaurantmodel.dao.ReseniaDAO;
import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.District;
import com.example.restaurantmodel.model.Menu;
import com.example.restaurantmodel.model.Platos;
import com.example.restaurantmodel.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 8/2/2016.
 */
public class RestaurantDaoImpl implements RestaurantDao {

    Context context;

    public RestaurantDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public long insert(Restaurant rest) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,rest.getName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,rest.getHorario());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,rest.getEmail());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,rest.getPhone());
        //content.put(RestaurantSchemaContract.Restaurant.COLUMN_MENU,rest.getMenu().getId());
        //content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_VOTE,rest.getVotos());
        //content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID,rest.getPhotoid());
       // content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,rest.getDistrict().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,rest.getAddress());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,rest.getLatitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,rest.getLongitude());
        //content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,rest.getPhoto());
        long id = sqlite.insert(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,content);
        //insert other restaurant properties like category
        //importante cerrar la conexion
        sqlite.close();

        return id;
    }

    @Override
    public long update(Restaurant rest) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,rest.getName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,rest.getHorario());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,rest.getEmail());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,rest.getPhone());
        //content.put(RestaurantSchemaContract.Restaurant.COLUMN_MENU,rest.getMenu().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_VOTE,rest.getVotos());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,rest.getDistrict().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,rest.getAddress());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,rest.getLatitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,rest.getLongitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID,rest.getPhotoid());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,rest.getPhoto());

        String whereClause = RestaurantSchemaContract.Restaurant._ID+" = ?";
        String[] args = {""+rest.getId()};

        long id = sqlite.update(RestaurantSchemaContract.Restaurant.TABLE_NAME,content,whereClause,args);
        sqlite.close();

        return id;
    }

    @Override
    public long delete(Restaurant restaurant) {
        return 0;
    }


    @Override
    public Restaurant get(int id) {
        // restaurant
        Restaurant rest = getRestaurant(id);
        Log.d("DAORESTAURANT",rest.getName());
        //CATEGORY
        rest.setCategories(getRestaurantCategories(id));
        Log.d("DAORESTAURANT",rest.getCategories().get(0).getName());
        //DISTRICT
        rest.setDistrict(getDistrict(rest.getDistrict().getId()));
        Log.d("DAORESTAURANT",rest.getDistrict().getName());
        // MENU
        rest.setMenu(getRestaurantMenu(id));
        if (rest.getMenu()!=null){
            Log.d("DAORESTAURANT","menu: "+rest.getMenu().size());
        }
        //COMMENTS
        rest.setResena(getRestaurantComment(id));
        if (rest.getResena()!=null){
            Log.d("DAORESTAURANT","comment: "+rest.getResena().size());
        }
        //PLATOS
        rest.setUserPhotos(getRestaurantDish(id));
        if (rest.getUserPhotos()!=null){
            Log.d("DAORESTAURANT","platos: "+rest.getUserPhotos().size());
        }
        return rest;
    }

    @Override
    public List<Restaurant> list() {
        List<Restaurant> allRestaurants = getRestaurantList();
        Log.d("DAO","LIST: "+allRestaurants.size());
        //District AND Category
        for (Restaurant rest:allRestaurants){
            int district_id = rest.getDistrict().getId();
            Log.d("DAO","LIST: "+district_id);
            rest.setDistrict(getDistrict(district_id));
            Log.d("DAO","LIST: "+getRestaurantCategories(rest.getId()));
            rest.setCategories(getRestaurantCategories(rest.getId()));
        }
        //PUEDE SER NECESARIO SETEAR OTRAS COSAS
        return allRestaurants;
    }

    private Restaurant getRestaurant(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Restaurant._ID+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        Restaurant rest = new Restaurant();
        int size=cursor.getCount();
        if(size==1){
            cursor.moveToNext();
            rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant._ID)));
            rest.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_NAME)));
            rest.setHorario(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO)));
            rest.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL)));
            rest.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHONE)));
            rest.setAvg_ranking(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_RANKING)));
            rest.setAvg_price(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE)));
            rest.setVotos(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_VOTE)));
            //DISTRICT
            int districtid= cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT));
            rest.setDistrict(new District(districtid));
            rest.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS)));
            rest.setLatitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE)));
            rest.setLongitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE)));
            //PHOTO COLUMNS
            rest.setPhotoid(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID)));
            rest.setPhoto(cursor.getBlob(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO)));
        } else {
            rest = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return rest;
    }

    //COLOCAR COMENTARIOS AQUI
    private List<Category> getRestaurantCategories(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        List<Integer> cat_list = new ArrayList<Integer>();

        String whereClause =RestaurantSchemaContract.Restaurant_Categories.COLUMN_RESTAURANT+"=?";
        String[] whereArgs =new String[]{""+id};
        Cursor cursor = sqlite.query(RestaurantSchemaContract.Restaurant_Categories.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                cat_list.add(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant_Categories.COLUMN_CATEGORIES)));
            } while (cursor.moveToNext());
        }
        Log.d("DAO","LIST: CAT: "+cat_list.size());
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        List<Category> list = new ArrayList<Category>();
        CategoryDistrictDao catDao = new CategoryDistrictDaoImpl(context);
        for (Integer catid:cat_list){
            Category cat = catDao.getCategory(catid);
            list.add(cat);
        }

        return list;
    }
//
//    public List<Restaurant> listarRestaurantes() {
//        AppRestSqlOpenHelper mySqlOpenHelper= new AppRestSqlOpenHelper(context);
//        SQLiteDatabase db=mySqlOpenHelper.getWritableDatabase();
//        String campos[] = {RestaurantSchemaContract.Restaurant.COLUMN_NAME, RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID,RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID,RestaurantSchemaContract.Restaurant.COLUMN_RANKING,RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,RestaurantSchemaContract.Restaurant.COLUMN_HORARIO};
//        Cursor cursor = db.query("restaurant",campos,null,null,null,null ,null ,null);
//        int size = cursor.getCount();
//        List<Restaurant> objetos = new ArrayList<>();
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Restaurant o = new Restaurant();
//            o.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_NAME)));
//            o.setPhotoid(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID)));
//            o.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID)));
//            o.setAvg_ranking(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_RANKING)));
//            o.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL)));
//            o.setAvg_price(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE)));
//            o.setWebpage(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO)));
//            objetos.add(o);
//            cursor.moveToNext();
//        }
//        return objetos;
//    }
//
  private   List<Restaurant> getRestaurantList() {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,null,null,null,null,null);
        List<Restaurant> list = new ArrayList<Restaurant>();
        if (cursor.moveToFirst()) {
            do {
                Restaurant rest = new Restaurant();
                rest.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant._ID)));
                rest.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_NAME)));
                rest.setHorario(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO)));
                rest.setEmail(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL)));
                rest.setPhone(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHONE)));
                rest.setAvg_ranking(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_RANKING)));
                rest.setAvg_price(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE)));
                rest.setVotos(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_VOTE)));
                //DISTRICT
                int districtid= cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT));
                rest.setDistrict(new District(districtid));

                rest.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS)));
                rest.setLatitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE)));
                rest.setLongitude(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE)));
                //PHOTO COLUMNS
                rest.setPhotoid(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO_ID)));
                rest.setPhoto(cursor.getBlob(cursor.getColumnIndex(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO)));

                list.add(rest);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    private List<Menu> getRestaurantMenu(int id) {
        AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        String whereClause = RestaurantSchemaContract.Menu.COLUMN_RESTAURANT+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = sqlite.query(RestaurantSchemaContract.Menu.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        List<Menu> list = new ArrayList<Menu>();
        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu();
                menu.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Menu._ID)));
                menu.setName(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Menu.COLUMN_NAME)));
                menu.setPrice(cursor.getDouble(cursor.getColumnIndex(RestaurantSchemaContract.Menu.COLUMN_PRICE)));
                menu.setIdRestaurant(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Menu.COLUMN_RESTAURANT)));
                list.add(menu);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return list;
    }

    private District getDistrict(int districtid) {
        CategoryDistrictDao catDao = new CategoryDistrictDaoImpl(context);
        return catDao.getDistrict(districtid);
    }

    private List<Platos> getRestaurantDish(int id) {
        //create dao for dish
        PlatosDao dao = new PlatosDaoImpl(context);
        //exec method for list
        List<Platos> list = dao.listByRestaurantId(id);
        return list;
    }

    private List<Commentary> getRestaurantComment(int id) {
        ReseniaDAO dao = new ReseniaDaoImpl(context);
        return dao.getCommentByRestaurantId(id);
    }

}

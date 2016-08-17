package com.example.restaurantmodel.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.AppRestSqlOpenHelper;
import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.ReseniaDAO;
import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Menu;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public class ReseniaDaoImpl implements ReseniaDAO {

    AppRestSqlOpenHelper appRestSqlOpenHelper;
    Context context;

    public ReseniaDaoImpl(Context context){
        appRestSqlOpenHelper = new AppRestSqlOpenHelper(context);
        this.context = context;
    }

    @Override
    public long insertarResenia(Commentary commentary) {
        SQLiteDatabase sqlite =  appRestSqlOpenHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Comment.COLUMN_USER, commentary.getUser().getId());
        content.put(RestaurantSchemaContract.Comment.COLUMN_RESTAURANT, commentary.getRestaurant().getId());
        content.put(RestaurantSchemaContract.Comment.COLUMN_RANKING, commentary.getRanking());
        content.put(RestaurantSchemaContract.Comment.COLUMN_PRICE, commentary.getPrice());
        content.put(RestaurantSchemaContract.Comment.COLUMN_COMMENT, commentary.getComment());
        content.put(RestaurantSchemaContract.Comment.COLUMN_DATE, commentary.getDate().toString());
        //content.put(RestaurantSchemaContract.Comment.COLUMN_IMAGEN, commentary.getImagen());
        long id = sqlite.insert(RestaurantSchemaContract.Comment.TABLE_NAME,null,content);
        sqlite.close();
        return id;
    }

    @Override
    public List<Commentary> listarResenia() {
        SQLiteDatabase db=appRestSqlOpenHelper.getWritableDatabase();
        String campos[] = {RestaurantSchemaContract.Comment._ID, RestaurantSchemaContract.Comment.COLUMN_COMMENT};
        Cursor cursor = db.query(RestaurantSchemaContract.Comment.TABLE_NAME,campos,null,null,null,null ,null ,null);
        int size = cursor.getCount();
        List<Commentary> objetos = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Commentary o = new Commentary();
            o.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment._ID)));
            o.setComment(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_COMMENT)));
            objetos.add(o);
            cursor.moveToNext();
        }
        return objetos;
    }

    @Override
    public List<Commentary> getCommentByRestaurantId(int id) {
        SQLiteDatabase db=appRestSqlOpenHelper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Comment.COLUMN_RESTAURANT+"=?";
        String[] whereArgs = new String[]{""+id};

        Cursor cursor = db.query(RestaurantSchemaContract.Comment.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        List<Commentary> list = new ArrayList<Commentary>();
        if (cursor.moveToFirst()) {
            do {
                Commentary comment = new Commentary();
                comment.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment._ID)));
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_USER)));
                comment.setUser(user); //SETEAR LUEGO EL USUARIO
                Restaurant r = new Restaurant();
                r.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_RESTAURANT)));
                comment.setRestaurant(r);
                comment.setRanking(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_RANKING)));
                comment.setPrice(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_PRICE))); //VALIDAR
                comment.setComment(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_COMMENT)));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_DATE))); //VALIDAR
                comment.setDate(date);
                list.add(comment);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();

        //SetUser
        for (Commentary comm:list){
            UserDao dao = new UserDaoImpl(context);
            comm.setUser(dao.get(comm.getUser().getId()));
        }

        return list;
    }

    @Override
    public List<Commentary> getCommentByUserId(int userid) {
        SQLiteDatabase db = appRestSqlOpenHelper.getWritableDatabase();
        String whereClause = RestaurantSchemaContract.Comment.COLUMN_USER + "=?";
        String[] whereArgs = new String[]{"" + userid};

        Cursor cursor = db.query(RestaurantSchemaContract.Comment.TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        List<Commentary> list = new ArrayList<Commentary>();
        if (cursor.moveToFirst()) {
            do {
                Commentary comment = new Commentary();
                comment.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment._ID)));
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_USER)));
                comment.setUser(user); //SETEAR LUEGO EL USUARIO
                Restaurant r = new Restaurant();
                r.setId(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_RESTAURANT)));
                comment.setRestaurant(r);//setear luego el restaurante
                comment.setRanking(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_RANKING)));
                comment.setPrice(cursor.getInt(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_PRICE))); //VALIDAR
                comment.setComment(cursor.getString(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_COMMENT)));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(RestaurantSchemaContract.Comment.COLUMN_DATE))); //VALIDAR
                comment.setDate(date);
                list.add(comment);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();

        //SetREstaurant
        for (Commentary comm : list) {
            RestaurantDao dao = new RestaurantDaoImpl(context);
            comm.setRestaurant(dao.get(comm.getRestaurant().getId()));
        }

        return list;
    }

    @Override
    public int eliminarResenia(Commentary commentary) {
        return 0;
    }

    @Override
    public Commentary obtenerResenia(Commentary commentary) {
        return null;
    }


}

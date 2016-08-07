package com.example.restaurantmodel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.model.Commentary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public class ReseniaDaoImpl implements ReseniaDAO{

    AppRestSqlOpenHelper appRestSqlOpenHelper;

    public ReseniaDaoImpl(Context context){ appRestSqlOpenHelper = new AppRestSqlOpenHelper(context);}

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
        content.put(RestaurantSchemaContract.Comment.COLUMN_IMAGEN, commentary.getImagen());
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
    public int actualizarResenia(Commentary commentary) {
        return 0;
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

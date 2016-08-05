package com.example.restaurantmodel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.model.Resenia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public class ReseniaDaoImpl implements ReseniaDAO{

    AppRestSqlOpenHelper appRestSqlOpenHelper;

    public ReseniaDaoImpl(Context context){ appRestSqlOpenHelper = new AppRestSqlOpenHelper(context);}

    @Override
    public long insertarResenia(Resenia resenia) {

        SQLiteDatabase sqlite =  appRestSqlOpenHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Resenia.COLUMN_RESTAURANT,resenia.getRestaurant().getId());
        content.put(RestaurantSchemaContract.Resenia.COLUMN_USER,resenia.getUser().getId());
        content.put(RestaurantSchemaContract.Resenia.COLUMN_RANKING,resenia.getRanking());
        content.put(RestaurantSchemaContract.Resenia.COLUMN_PRECIO, resenia.getPrice());
        content.put(RestaurantSchemaContract.Resenia.COLUMN_COMENTARIO,resenia.getComment());
        content.put(RestaurantSchemaContract.Resenia.COLUMN_IMAGEN,resenia.getImagen());

        long id = sqlite.insert(RestaurantSchemaContract.Resenia.TABLE_NAME,null,content);
        sqlite.close();
        return id;
    }

    @Override
    public int actualizarResenia(Resenia resenia) {
        return 0;
    }

    @Override
    public int eliminarResenia(Resenia resenia) {
        return 0;
    }

    @Override
    public Resenia obtenerResenia(Resenia resenia) {
        return null;
    }

    @Override
    public List<Resenia> listarResenia() {
       SQLiteDatabase db=appRestSqlOpenHelper.getWritableDatabase();
         String campos[] = {"id", "nombre"};
        Cursor cursor = db.query("mytable",campos,null,null,null,null ,null ,null);
        int size = cursor.getCount();
        List<Resenia> objetos = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
           Resenia o = new Resenia();
            o.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            o.setComment(cursor.getString(cursor.getColumnIndex("nombre")));
            objetos.add(o);
            cursor.moveToNext();
        }
        return objetos;
    }
}

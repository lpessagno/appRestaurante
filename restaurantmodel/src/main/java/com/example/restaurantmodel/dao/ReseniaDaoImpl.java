package com.example.restaurantmodel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.model.Resenia;

import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public class ReseniaDaoImpl implements ReseniaDAO{

    AppRestSqlOpenHelper appRestSqlOpenHelper;

    public ReseniaDaoImpl(Context context){ appRestSqlOpenHelper = new AppRestSqlOpenHelper(context);}

    @Override
    public long insertarResenia(Resenia resenia) {

       /* AppRestSqlOpenHelper helper = new AppRestSqlOpenHelper(context);
        SQLiteDatabase sqlite = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_NAME,rest.getName());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_HORARIO,rest.getHorario());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_EMAIL,rest.getEmail());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHONE,rest.getPhone());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_MENU,rest.getMenu().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_RANKING,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE,rest.getAvg_ranking());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT,rest.getDistrict().getId());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_ADDRESS,rest.getAddress());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LATITUDE,rest.getLatitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_LONGITUDE,rest.getLongitude());
        content.put(RestaurantSchemaContract.Restaurant.COLUMN_PHOTO,rest.getPhotoid());

        long id = sqlite.insert(RestaurantSchemaContract.Restaurant.TABLE_NAME,null,content);

        //insert other restaurant properties like category

        //importante cerrar la conexion
        sqlite.close();
*/
        return 0;
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
        return null;
    }
}

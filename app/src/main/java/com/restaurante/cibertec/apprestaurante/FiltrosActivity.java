package com.restaurante.cibertec.apprestaurante;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restaurantmodel.contract.RestaurantSchemaContract;
import com.example.restaurantmodel.dao.CategoryDistrictDao;
import com.example.restaurantmodel.impl.CategoryDistrictDaoImpl;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.District;

import java.util.List;

public class FiltrosActivity extends AppCompatActivity {

    public static final String ORDENAR = "ordenar";
    //public static final String ORD_COMIDA = RestaurantSchemaContract.Restaurant.;
    public static final String ORD_DISTANCIA = RestaurantSchemaContract.Restaurant.COLUMN_DISTRICT;
    public static final String ORD_PRECIO = RestaurantSchemaContract.Restaurant.COLUMN_AVG_PRICE;
    public static final String ORD_RANKING = RestaurantSchemaContract.Restaurant.COLUMN_RANKING;

    public static final String DISTRITOSID = "DISTRITOSID";
    public static String idsDistricts;
    SharedPreferences appPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);
        editor = appPreferences.edit();
        editor.putString(ORDENAR, "");
        editor.commit();
    }

    public void seleccionado(View view) {
        ImageView imageView = (ImageView) view;
        imageView.setColorFilter(Color.rgb(255, 0, 0));
        editor = appPreferences.edit();

        /*if (imageView.getId() == R.id.btnComida) {
            ImageView temp = (ImageView) findViewById(R.id.btnDistancia);
            temp.setColorFilter(0);
            temp = (ImageView) findViewById(R.id.btnPrecio);
            temp.setColorFilter(0);
            temp = (ImageView) findViewById(R.id.btnRanking);
            temp.setColorFilter(0);
            editor.putString(ORDENAR, ORD_COMIDA);
        }*/

        if (imageView.getId() == R.id.btnDistancia) {
            ImageView temp = (ImageView) findViewById(R.id.btnPrecio);
            temp.setColorFilter(0);
           /* temp = (ImageView) findViewById(R.id.btnComida);
            temp.setColorFilter(0);*/
            temp = (ImageView) findViewById(R.id.btnRanking);
            temp.setColorFilter(0);
            editor.putString(ORDENAR,  ORD_DISTANCIA);
        }

        if (imageView.getId() == R.id.btnPrecio) {
            ImageView temp = (ImageView) findViewById(R.id.btnDistancia);
            temp.setColorFilter(0);
            /*temp = (ImageView) findViewById(R.id.btnComida);
            temp.setColorFilter(0);*/
            temp = (ImageView) findViewById(R.id.btnRanking);
            temp.setColorFilter(0);
            editor.putString(ORDENAR,  ORD_PRECIO);
        }


        if (imageView.getId() == R.id.btnRanking) {
            ImageView temp = (ImageView) findViewById(R.id.btnDistancia);
            temp.setColorFilter(0);
            temp = (ImageView) findViewById(R.id.btnPrecio);
            temp.setColorFilter(0);
           /* temp = (ImageView) findViewById(R.id.btnComida);
            temp.setColorFilter(0);*/
            editor.putString(ORDENAR, ORD_RANKING);
        }
        editor.commit();

    }

    public void elegirTiposComida(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tipos de Comida");
        List<Category> categorias = getTiposComida();
        final String[] opciones = new String[categorias.size()];
        final boolean[] arreglo = new boolean[categorias.size()];

        for (int i = 0; i < categorias.size(); i++) {
            opciones[i]  = categorias.get(i).getName();
            arreglo[i] = false;
        }
        builder.setMultiChoiceItems(opciones, arreglo, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    arreglo[which] = true;
                } else {
                    arreglo[which] = false;
                }
            }
        });
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String texto = "";
                for (int i = 0; i < arreglo.length; i++) {
                    if (arreglo[i] == true) {
                        texto += " " + opciones[i];
                    }
                }
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void elegirDistritos(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Distritos");
        List<District> distritos = getDistritos();
        final String[] opciones = new String[distritos.size()];
        final int[] ids = new int[distritos.size()];
        final boolean[] arreglo = new boolean[distritos.size()];

        for (int i = 0; i < distritos.size(); i++) {
            ids[i] = distritos.get(i).getId();
            opciones[i]  = distritos.get(i).getName();
            arreglo[i] = false;
        }
        builder.setMultiChoiceItems(opciones, arreglo, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    arreglo[which] = true;
                } else {
                    arreglo[which] = false;
                }
            }
        });
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor = appPreferences.edit();
                String idsDistricts = "";
                for (int i = 0; i < arreglo.length; i++) {
                    if (arreglo[i] == true) {
                        System.out.println("seleccionado: " + arreglo[i]);
                        idsDistricts = idsDistricts + ids[i]+ ",";
                    }
                }
                editor.putString(DISTRITOSID, idsDistricts);
                editor.commit();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putString(DISTRITOSID, "");
                editor.commit();
            }
        });
        builder.create().show();
    }


    private List<District> getDistritos() {
        CategoryDistrictDao dao = new CategoryDistrictDaoImpl(this);
        List<District> list = dao.listDistrict();
        return list;
    }

    private List<Category> getTiposComida() {
        CategoryDistrictDao dao = new CategoryDistrictDaoImpl(this);
        List<Category> list = dao.listCategory() ;
        return list;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_filtros , menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int idopcion = item.getItemId();
        switch (idopcion) {
            case R.id.filtrar:
                Intent intent = new Intent(this, BienvenidaActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

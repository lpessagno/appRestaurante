package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.impl.RestaurantDaoImpl;
import com.example.restaurantmodel.model.Category;
import com.example.restaurantmodel.model.Restaurant;
import com.restaurante.cibertec.recyclers.RestauranteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BienvenidaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    SharedPreferences appPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();*/
//                //grabar();
//                //listar();
//            }
//        });

        listar();

    }

    public void listar() {
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Restaurant> list = getRestaurtants();
        String distritos = appPreferences.getString(FiltrosActivity.DISTRITOSID, "vacio");
        String ordenar = appPreferences.getString(FiltrosActivity.ORDENAR, "vacio");
        String categorias = appPreferences.getString(FiltrosActivity.CATEGORIASID, "vacio");
        int[] idsDistricts;
        String[] idsDistritos;
        if (!distritos.equals("vacio") && !distritos.trim().equals("")) {
            idsDistritos = distritos.split(",");
            idsDistricts = new int[idsDistritos.length];
            for (int i = 0; i < idsDistritos.length; i++) {
                idsDistricts[i] = Integer.parseInt(idsDistritos[i]);
            }
        } else {
            idsDistricts = new int[0];
        }

        String[] idsCategories;
        List<Category> idsCat = new ArrayList<Category>();
        Log.d("prueba",categorias);
        if (!categorias.equals("vacio") && !categorias.trim().equals("")) {
            idsCategories = categorias.split(",");
            for (int i = 0; i < idsCategories.length; i++) {
                Log.d("prueba",idsCategories[i]);
                Category category = new Category();
                category.setId(Integer.parseInt(idsCategories[i]));
                idsCat.add(category);
            }
        } else {
            idsCat =null;
        }

        if (ordenar == null || ordenar.trim().equals("") || ordenar.equals("vacio")) {
            ordenar = "";
        }
        list = getRestaurtantsFiltro(idsDistricts, ordenar, idsCat);
        RecyclerView.Adapter adapter1 = new RestauranteRecyclerAdapter(this, list); //cambiar el dato de entrada
        recyclerView.setAdapter(adapter1);
    }

    private List<Restaurant> getRestaurtants() {
        RestaurantDao dao = new RestaurantDaoImpl(this);
        List<Restaurant> list = dao.list();
        return list;
    }

    private List<Restaurant> getRestaurtantsFiltro(int[] ids, String orderBy, List<Category> categories) {
        Log.d("DAOS", "getRestaurtants");
        RestaurantDao dao = new RestaurantDaoImpl(this);
        List<Restaurant> list = dao.listByFiltro(ids, categories, orderBy);
        return list;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int idopcion = item.getItemId();
        switch (idopcion) {
           /* case R.id.opt1:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"Opcion 1",Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.opt2:
                String loginUser = appPreferences.getString(getString(R.string.user),getString(R.string.default_string));
                if(!loginUser.equals(getString(R.string.default_string))){ //HAY UN PERFIL LOGUEADO?
                    Intent intent2 = new Intent(this,PerfActivity.class);
                    startActivity(intent2);
                } else {
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.opt3:
                Intent intent3 = new Intent(this, FiltrosActivity.class);
                startActivity(intent3);
                break;
            case R.id.opt4:
                String logname = appPreferences.getString(getString(R.string.user),getString(R.string.default_string));
                if (!logname.equals(getString(R.string.default_string))){
                    editor = appPreferences.edit();
                    editor.putString(getString(R.string.user),getString(R.string.default_string));
                    editor.putString(getString(R.string.password),getString(R.string.default_string));
                    editor.putInt(getString(R.string.userid),0);
                    editor.commit();
                } else {
                    Toast.makeText(this,"NO USER",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

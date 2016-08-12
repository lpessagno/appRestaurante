package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.impl.RestaurantDaoImpl;
import com.example.restaurantmodel.model.Restaurant;
import com.restaurante.cibertec.recyclers.RestauranteRecyclerAdapter;

import java.util.List;

public class BienvenidaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    public void grabar()
    {
        RestaurantDaoImpl mydao = new RestaurantDaoImpl(this);
        Restaurant o= new Restaurant();
        o.setName("Ole");
        o.setHorario("www.wwww.www");
        o.setEmail("ssss");
        o.setPhone("1212121");
        o.setAvg_price(12);
        o.setAddress("Av.canevaro 178");
        o.setLatitude("-12.0912905");
        o.setLongitude("-77.0502661");
        mydao.insert(o);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //grabar();
                //listar();
            }
        });

        listar();

    }

    public void listar()
    {
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RestaurantDaoImpl mydao = new RestaurantDaoImpl(this);
        //List<Restaurant> list = mydao.listarRestaurantes();
        List<Restaurant> list = getRestaurtants();
        RecyclerView.Adapter adapter1 = new RestauranteRecyclerAdapter(this,list); //cambiar el dato de entrada
        recyclerView.setAdapter(adapter1);
    }

    private List<Restaurant> getRestaurtants() {
        Log.d("DAOS","getRestaurtants");
        RestaurantDao dao = new RestaurantDaoImpl(this);
        List<Restaurant> list = dao.list();
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
            case R.id.opt1:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"Opcion 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opt2:
                Intent intent2 = new Intent(this,PerfActivity.class);
                startActivity(intent2);
                //Toast.makeText(this,"Opcion 2",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.impl.RestaurantDaoImpl;
import com.example.restaurantmodel.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class BienvenidaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //String data[] = {"Ebisu", "La Bistecca"};
        //String ubicacion[] = {"San Isidro", "San Isidro"};
        //String tipoComida[] = {"Japonesa", "Grill"};
        //double precio[] = {65, 78};
        //String distancia[] = {"175m", "234m"};
        //double ranking[] = {4.7, 4.3};
        //int votos[] = {5, 3};


        List<Restaurante> restaurantes = new ArrayList<>();
       // for (int i = 0; i < data.length; i++) {
         //   Restaurante restaurante = new Restaurante();
           // restaurante.setNombre(data[i]);
           // restaurante.setUbicacion(ubicacion[i]);
      //      restaurante.setImagen(getResources().getIdentifier(restaurante.getNombre().toLowerCase().replace(" ", ""), "drawable", getPackageName()));
        //    restaurante.setTipoComida(tipoComida[i]);
          //  restaurante.setPrecio(precio[i]);
          //  restaurante.setDistancia(distancia[i]);
           // restaurante.setRanking(ranking[i]);
          //  restaurante.setVotos(votos[i]);
          //  restaurantes.add(restaurante);
        //}
        List<Restaurant> rest = getRestaurtants();

        RecyclerView.Adapter adapter1 = new RestauranteRecyclerAdapter(this, rest); //cambiar el dato de entrada
        recyclerView.setAdapter(adapter1);
    }

    private List<Restaurant> getRestaurtants() {
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

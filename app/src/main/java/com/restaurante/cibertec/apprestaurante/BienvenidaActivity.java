package com.restaurante.cibertec.apprestaurante;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

        String data[] = {"Ebisu", "La Bistecca"};
        String ubicacion[] = {"San Isidro", "San Isidro"};
        String tipoComida[] = {"Japonesa", "Grill"};
        double precio[] = {65, 78};
        String distancia[] = {"175m", "234m"};
        double ranking[] = {4.7, 4.3};
        int votos[] = {5, 3};


        List<Restaurante> restaurantes = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            Restaurante restaurante = new Restaurante();
            restaurante.setNombre(data[i]);
            restaurante.setUbicacion(ubicacion[i]);
            restaurante.setImagen(getResources().getIdentifier(restaurante.getNombre().toLowerCase().replace(" ", ""), "drawable", getPackageName()));
            restaurante.setTipoComida(tipoComida[i]);
            restaurante.setPrecio(precio[i]);
            restaurante.setDistancia(distancia[i]);
            restaurante.setRanking(ranking[i]);
            restaurante.setVotos(votos[i]);
            restaurantes.add(restaurante);

        }

        RecyclerView.Adapter adapter1 = new RestauranteRecyclerAdapter(this, restaurantes);
        recyclerView.setAdapter(adapter1);
    }


}

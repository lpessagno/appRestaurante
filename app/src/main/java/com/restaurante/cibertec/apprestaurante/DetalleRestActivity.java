package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetalleRestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_rest);


        Intent intent= getIntent();
        String nombre= intent.getStringExtra(RestauranteRecyclerAdapter.NOMBRE);
        String ubicacion= intent.getStringExtra(RestauranteRecyclerAdapter.UBICACION);
        int imagen=intent.getIntExtra(RestauranteRecyclerAdapter.IMAGEN,0);
        String tipoComida= intent.getStringExtra(RestauranteRecyclerAdapter.TIPOCOMIDA);
        String precio= intent.getStringExtra(RestauranteRecyclerAdapter.PRECIO);
        String votos= intent.getStringExtra(RestauranteRecyclerAdapter.VOTOS);
        String ranking= intent.getStringExtra(RestauranteRecyclerAdapter.RANKING);
        String distancia= intent.getStringExtra(RestauranteRecyclerAdapter.DISTANCIA);

    }
}

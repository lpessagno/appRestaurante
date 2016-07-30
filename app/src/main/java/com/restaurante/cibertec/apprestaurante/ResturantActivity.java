package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TabHost;

import com.example.restaurantmodel.model.Platos;

import java.util.ArrayList;
import java.util.List;

public class ResturantActivity extends AppCompatActivity {

    TabHost tabHost;
    TabHost.TabSpec tabSpec;
    RecyclerView lista_platos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);

        tabHost=(TabHost)findViewById(R.id.tabpanel);
        tabHost.setup();

        lista_platos=(RecyclerView) findViewById(R.id.lista_platos);
        lista_platos.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_platos=new PlatosAdapter(this,getPlatos());
        lista_platos.setAdapter(adapter_platos);


        TabHost.TabSpec spec1 = tabHost.newTabSpec("Comentarios");

        spec1.setContent(R.id.tab_comentarios);
        spec1.setIndicator("Reseñas");

        tabHost.addTab(spec1);


        TabHost.TabSpec spec2 = tabHost.newTabSpec("Fotos");

        spec2.setContent(R.id.panel_fotos);
        spec2.setIndicator("Fotos");

        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("Menú");

        spec3.setContent(R.id.tab_menu);
        spec3.setIndicator("Menu");

        tabHost.addTab(spec3);



    }

    public void subirFoto(View view) {
        Intent intent= new Intent(getApplicationContext(),FotosComidaActivity.class);
        startActivity(intent);
    }

    public void hacerResena(View view) {
        Intent intent = new Intent(getApplicationContext(),ResenaActivity.class);
        startActivity(intent);
    }



    //TODO: este método desaparece cuando este la base de datos
    public List<Platos> getPlatos(){
        List<Platos> platos= new ArrayList<Platos>();
        Platos p1= new Platos();
        p1.setDescription("Causa de Atún");

        Platos p2= new Platos();
        p2.setDescription("Lomito Saltado");

        platos.add(p1);
        platos.add(p2);

        return platos;
    }
}

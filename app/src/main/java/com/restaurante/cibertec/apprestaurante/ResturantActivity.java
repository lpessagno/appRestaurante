package com.restaurante.cibertec.apprestaurante;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.restaurantmodel.model.*;
import com.example.restaurantmodel.model.Commentary;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.restaurante.cibertec.recyclers.ComentariosAdapter;
import com.restaurante.cibertec.recyclers.MenusAdapter;
import com.restaurante.cibertec.recyclers.PlatosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResturantActivity extends AppCompatActivity {

    TabHost tabHost;
    TabHost.TabSpec tabSpec;
    RecyclerView lista_platos;
    RecyclerView lista_comentarios;
    RecyclerView lista_menus;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);

        tabHost = (TabHost) findViewById(R.id.tabpanel);
        tabHost.setup();

        lista_comentarios = (RecyclerView) findViewById(R.id.lista_comentarios);
        lista_comentarios.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_comentarios = new ComentariosAdapter(this, getComentarios());
        lista_comentarios.setAdapter(adapter_comentarios);


        lista_platos = (RecyclerView) findViewById(R.id.lista_platos);
        lista_platos.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_platos = new PlatosAdapter(this, getPlatos());
        lista_platos.setAdapter(adapter_platos);


        lista_menus = (RecyclerView) findViewById(R.id.lista_menus);
        lista_menus.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_menus = new MenusAdapter(this, getMenus());
        lista_menus.setAdapter(adapter_menus);


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

        ///@
        /*
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamar();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Toast toast2 = Toast.makeText(getApplicationContext(), "Ejemplo 2", Toast.LENGTH_SHORT);
                toast2.show();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Toast toast3 = Toast.makeText(getApplicationContext(), "Ejemplo 3", Toast.LENGTH_SHORT);
                toast3.show();
            }
        });*/

    }

    public void subirFoto(View view) {
        DialogFragment dialog = new FotosDialog();
        dialog.show(getSupportFragmentManager(), "Fotos");
        // Intent intent= new Intent(getApplicationContext(),FotosComidaActivity.class);
        // startActivity(intent);
    }


    public void hacerResena(View view) {
        DialogFragment dialog = new ResenaDialog();
        dialog.show(getSupportFragmentManager(), "Resena");
//        Intent intent = new Intent(getApplicationContext(),ResenaActivity.class);
//        startActivity(intent);
    }


    //TODO: este método desaparece cuando este la base de datos
    public List<Platos> getPlatos() {
        List<Platos> platos = new ArrayList<Platos>();
        Platos p1 = new Platos();
        p1.setDescription("Causa de Atún");

        Platos p2 = new Platos();
        p2.setDescription("Lomito Saltado");

        platos.add(p1);
        platos.add(p2);

        return platos;
    }


    //TODO: este método desaparece cuando este la base de datos
    public List<Commentary> getComentarios() {
        List<Commentary> comentarios = new ArrayList<Commentary>();
        Commentary c1 = new Commentary();
        c1.setComment("EXCELENTE SITIO");
        User u = new User();
        u.setName("lpessagno");
        c1.setUser(u);

        Commentary c2 = new Commentary();
        c2.setComment("RECOMENDADO");
        User u2 = new User();
        u2.setName("jkrentzien");
        c2.setUser(u2);

        comentarios.add(c1);
        comentarios.add(c2);

        return comentarios;
    }


    //TODO: este método desaparece cuando este la base de datos
    public List<Menu> getMenus() {
        List<Menu> menus = new ArrayList<Menu>();

        Menu m1 = new Menu();
        m1.setName("Lomo Saltado");
        m1.setPrice(25.0);

        Menu m2 = new Menu();
        m2.setName("Ají de Gallina");
        m2.setPrice(25.0);

        menus.add(m1);
        menus.add(m2);

        return menus;
    }
    //@
    public void llamar() {
        int permisoWriteExternalCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
        if (permisoWriteExternalCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},12);
        }
        else {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:(043) 631641")); // pasar telefono del Restaurante
        startActivity(intent);
        }
    }



}

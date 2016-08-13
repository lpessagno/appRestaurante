package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.restaurantmodel.model.*;
import com.example.restaurantmodel.model.Commentary;
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

    private static final int REQUEST_CAPTURA =111 ;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);

        tabHost=(TabHost)findViewById(R.id.tabpanel);
        tabHost.setup();

        lista_comentarios=(RecyclerView) findViewById(R.id.lista_comentarios);
        lista_comentarios.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_comentarios=new ComentariosAdapter(this,getComentarios());
        lista_comentarios.setAdapter(adapter_comentarios);



        lista_platos=(RecyclerView) findViewById(R.id.lista_platos);
        lista_platos.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_platos=new PlatosAdapter(this,getPlatos());
        lista_platos.setAdapter(adapter_platos);


        lista_menus=(RecyclerView) findViewById(R.id.lista_menus);
        lista_menus.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_menus=new MenusAdapter(this,getMenus());
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



    }

    public void subirFoto(View view) {
        DialogFragment dialog = new FotosDialog();
        dialog.show(getSupportFragmentManager(),"Fotos");
       // Intent intent= new Intent(getApplicationContext(),FotosComidaActivity.class);
       // startActivity(intent);
    }



    public void hacerResena(View view) {
        DialogFragment dialog = new ResenaDialog();
        dialog.show(getSupportFragmentManager(),"Resena");
//        Intent intent = new Intent(getApplicationContext(),ResenaActivity.class);
//        startActivity(intent);
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




    //TODO: este método desaparece cuando este la base de datos
    public List<Commentary> getComentarios(){
        List<Commentary> comentarios= new ArrayList<Commentary>();
        Commentary c1= new Commentary();
        c1.setComment("EXCELENTE SITIO");
        User u= new User();
        u.setName("lpessagno");
        c1.setUser(u);

        Commentary c2= new Commentary();
        c2.setComment("RECOMENDADO");
        User u2= new User();
        u2.setName("jkrentzien");
        c2.setUser(u2);

       comentarios.add(c1);
        comentarios.add(c2);

        return comentarios;
    }


    //TODO: este método desaparece cuando este la base de datos
    public List<Menu> getMenus(){
        List<Menu> menus= new ArrayList<Menu>();

        Menu m1= new Menu();
        m1.setName("Lomo Saltado");
        m1.setPrice(25.0);

        Menu m2= new Menu();
        m2.setName("Ají de Gallina");
        m2.setPrice(25.0);

        menus.add(m1);
        menus.add(m2);

        return menus;
    }


    public void tomarFoto(View view) {
        Log.d("HOLA","HOLA A TODOS");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAPTURA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==111){
            if (resultCode==RESULT_OK){
                Bitmap foto = (Bitmap) data.getExtras().get("data");
                photo.setImageBitmap(foto);
            }
        }
    }
}

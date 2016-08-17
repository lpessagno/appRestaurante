package com.restaurante.cibertec.apprestaurante;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.impl.UserDaoImpl;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Platos;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;
import com.restaurante.cibertec.recyclers.PlatosAdapter;
import com.restaurante.cibertec.recyclers.RecyclerAdapterResenia;
import com.restaurante.cibertec.recyclers.RestauranteRecyclerAdapter;

import java.util.List;

public class PerfActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView recyclerView_fav;
    RecyclerView recyclerView_foto;
    TabHost tbh;
    TextView userName;

    SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perf);

        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);
        userName = (TextView)findViewById(R.id.txtname);

        User user = getUserForProfile();
        userName.setText(user.getName());

        tbh = (TabHost)findViewById(R.id.tabHost);
        tbh.setup();

        TabHost.TabSpec tab1 = tbh.newTabSpec("tab1");
        TabHost.TabSpec tab2 = tbh.newTabSpec("tab2");
        TabHost.TabSpec tab3 = tbh.newTabSpec("tab3");

        tab1.setIndicator("Reseñas");
        tab1.setContent(R.id.linearLayout);

        tab2.setIndicator("Fotos");
        tab2.setContent(R.id.linearLayout2);

        tab3.setIndicator("Favoritos");
        tab3.setContent(R.id.linearLayout3);

        tbh.addTab(tab1);
        tbh.addTab(tab2);
        tbh.addTab(tab3);

        recyclerView = (RecyclerView) findViewById(R.id.viewResenia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Commentary> comments = user.getCommentaries();

        RecyclerAdapterResenia adapterCustom = new RecyclerAdapterResenia(this, comments);
        recyclerView.setAdapter(adapterCustom);

        recyclerView_fav = (RecyclerView) findViewById(R.id.viewFavoritos);
        recyclerView_fav.setLayoutManager(new LinearLayoutManager(this));
        List<Restaurant> favoritos = user.getFavorites();

        RecyclerView.Adapter adapter1 = new RestauranteRecyclerAdapter(this, favoritos);
        recyclerView_fav.setAdapter(adapter1);

        recyclerView_foto = (RecyclerView) findViewById(R.id.viewFotos);
        recyclerView_foto.setLayoutManager(new LinearLayoutManager(this));
        List<Platos> fotos = user.getUserPhotos();

        PlatosAdapter adapterFotos = new PlatosAdapter(this, fotos);
        recyclerView_foto.setAdapter(adapterFotos);

    }

    private User getUserForProfile() {
        String username = appPreferences.getString(getString(R.string.user),getString(R.string.default_string));
        UserDao dao = new UserDaoImpl(this);
        User user = dao.getByName(username);
        return  user;
    }
}

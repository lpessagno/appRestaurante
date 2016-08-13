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
import com.example.restaurantmodel.model.User;
import com.restaurante.cibertec.recyclers.RecyclerAdapterResenia;

import java.util.ArrayList;
import java.util.List;

public class PerfActivity extends AppCompatActivity {
    RecyclerView recyclerView;
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

        /*tab2.setIndicator("Fotos");
        tab2.setContent(R.id.linearLayout2);

        tab3.setIndicator("Favoritos");
        tab3.setContent(R.id.linearLayout3);*/

        tbh.addTab(tab1);/* tbh.addTab(tab2);
        tbh.addTab(tab3);*/


//////
        recyclerView = (RecyclerView) findViewById(R.id.viewResenia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Commentary> comments = user.getCommentaries();

        /*final String[] nombre = {"China","Argentina","Peru"};
        final String[] fecha = {"24 JUN 2016","30 JUN 2016","15 JUL 2016"};
        final String[] descripcion = {"Estuvimos en el Chifa Mandarin para un almuerzo de sabado con un grupo grandem en lo cual fuismo muy bien atendidos por todo el staff.",
                "Estuvimos en el Restaurant Comida Gourmet El Argentino para un almuerzo de sabado con un grupo grande en lo cual fuimos muy bien atendidos por todo el staff.",
                "Estuvimos en el Restaurant Peruano para un almuerzo de sabado con un grupo grande en lo cual fuimos muy bien atendidos por todo el staff."};

        List<Resenia> resenialista = new ArrayList<Resenia>();
        for (int i=0; i<nombre.length;i++){
            Resenia res = new Resenia();
            res.setRestname(nombre[i]);
            res.setFecha(fecha[i]);
            res.setResenia(descripcion[i]);
            res.setImagen(getResources().getIdentifier(res.getRestname().toLowerCase(), "drawable", getPackageName()));
            resenialista.add(res);
        }*/

        RecyclerAdapterResenia adapterCustom = new RecyclerAdapterResenia(this, comments);
        recyclerView.setAdapter(adapterCustom);
        ////
    }

    private User getUserForProfile() {
        String username = appPreferences.getString(getString(R.string.user),getString(R.string.default_string));
        UserDao dao = new UserDaoImpl(this);
        User user = dao.getByName(username);
        return  user;
    }
}

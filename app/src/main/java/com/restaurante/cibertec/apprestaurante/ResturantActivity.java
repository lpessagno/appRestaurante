package com.restaurante.cibertec.apprestaurante;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmodel.dao.RestaurantDao;
import com.example.restaurantmodel.impl.RestaurantDaoImpl;
import com.example.restaurantmodel.model.*;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Menu;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.restaurante.cibertec.recyclers.ComentariosAdapter;
import com.restaurante.cibertec.recyclers.MenusAdapter;
import com.restaurante.cibertec.recyclers.PlatosAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ResturantActivity extends AppCompatActivity {

    public static final String  EXTRA_NAME="NAME";
    public static final String  EXTRA_LATITUD="LATITUD";
    public static final String  EXTRA_LONGITUD="LONGITUD";

    TabHost tabHost;
    TabHost.TabSpec tabSpec;
    RecyclerView lista_platos;
    RecyclerView lista_comentarios;
    RecyclerView lista_menus;

    private static final int REQUEST_CAPTURA =111 ;

    Restaurant restaurantDetail;
    int idRest;
    SharedPreferences appPreferences;
    SharedPreferences.Editor editor;

    //RestaurantViews
    TextView detailName;
    TextView detailCategory;
    TextView detailDistrict;
    TextView detailAddress;
    TextView detailPhoneNumber;
    TextView detailTime;
    ImageView detailPicture;
    TextView detailRank;
    TextView detailVotes;
    TextView detailPrice;
    TextView detailLatitud;
    TextView detailLongitud;


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;

    DialogFragment dialog_comida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);

        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);
        Intent intent = getIntent();
        idRest = intent.getIntExtra(getString(R.string.intentIdExtra),0);
        Log.d("RESTACTIVITY","ID: "+idRest);
        findRestaurantViews();

        restaurantDetail = getRestaurantData(idRest);
        setDetailData(restaurantDetail);

        editor = appPreferences.edit();
        editor.putInt(getString(R.string.restaurantid),restaurantDetail.getId());

        tabHost = (TabHost) findViewById(R.id.tabpanel);
        tabHost.setup();

        lista_comentarios = (RecyclerView) findViewById(R.id.lista_comentarios);
        lista_comentarios.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_comentarios = new ComentariosAdapter(this, getComentarios(restaurantDetail));
        lista_comentarios.setAdapter(adapter_comentarios);


        lista_platos = (RecyclerView) findViewById(R.id.lista_platos);
        lista_platos.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_platos = new PlatosAdapter(this, getPlatos(restaurantDetail));
        lista_platos.setAdapter(adapter_platos);


        lista_menus = (RecyclerView) findViewById(R.id.lista_menus);
        lista_menus.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView.Adapter adapter_menus = new MenusAdapter(this, getMenus(restaurantDetail));
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

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        //floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);

       /* floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamar();
            }
        });*/
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hacerResena(v);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Agregado a tus Favoritos", Toast.LENGTH_SHORT);
                toast3.show();
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              subirFoto(v);
            }
        });
    }

    private void findRestaurantViews() {
        detailName = (TextView)findViewById(R.id.restDetailName);
        detailCategory = (TextView) findViewById(R.id.restDetailCategory);
        detailDistrict = (TextView) findViewById(R.id.restDetailDistrict);
        detailAddress = (TextView) findViewById(R.id.restDetailAddress);
        detailPhoneNumber = (TextView) findViewById(R.id.restDetailPhoneNumber);
        detailTime = (TextView) findViewById(R.id.restDetailTime);
        detailPicture = (ImageView)findViewById(R.id.detailPicture);
        detailRank = (TextView)findViewById(R.id.detailAvgRank);
        detailVotes = (TextView)findViewById(R.id.detailVotes);
        detailPrice = (TextView)findViewById(R.id.detailAvgPrice);
        detailLatitud = (TextView)findViewById(R.id.restLatitud);
        detailLongitud = (TextView)findViewById(R.id.restLongitud);
    }

    private void setDetailData(Restaurant restaurantDetail) {
        detailName.setText(restaurantDetail.getName());
        String catstr = "";
        for (Category cat:restaurantDetail.getCategories()){
            catstr += cat.getName()+", ";
        }
        detailCategory.setText(catstr.substring(0,catstr.length()-2));
        detailDistrict.setText(restaurantDetail.getDistrict().getName());
        detailAddress.setText(restaurantDetail.getAddress());
        detailPhoneNumber.setText(restaurantDetail.getPhone());
        detailTime.setText(restaurantDetail.getHorario());
        Bitmap bm = BitmapFactory.decodeByteArray(restaurantDetail.getPhoto(),0,restaurantDetail.getPhoto().length);
        detailPicture.setImageBitmap(bm);
        detailRank.setText(""+restaurantDetail.getAvg_ranking());
        detailVotes.setText(""+restaurantDetail.getVotos());
        detailPrice.setText(""+restaurantDetail.getAvg_price());
        detailLatitud.setText(restaurantDetail.getLatitude());
        detailLongitud.setText(restaurantDetail.getLongitude());
    }

    private Restaurant getRestaurantData(int idRest) {
        RestaurantDao dao = new RestaurantDaoImpl(this);
        return dao.get(idRest);
    }

    public void subirFoto(View view) {
        String userlogged = appPreferences.getString(getString(R.string.user),getString(R.string.default_string));
        if (!userlogged.equals(getString(R.string.default_string))){
            dialog_comida = new FotosDialog();
            dialog_comida.show(getSupportFragmentManager(), "Fotos");
        } else {
            Toast.makeText(this,"No puedes subir fotos si no estas loggeado",Toast.LENGTH_SHORT).show();
        }
    }

    public void hacerResena(View view) {
        DialogFragment dialog = new ResenaDialog();
        dialog.show(getSupportFragmentManager(), "Resena");
    }

    public List<Platos> getPlatos(Restaurant rest) {
        List<Platos> platos = rest.getUserPhotos();

        return platos;
    }

    public List<Commentary> getComentarios(Restaurant rest) {
        List<Commentary> comentarios = rest.getResena();

        return comentarios;
    }

    public List<Menu> getMenus(Restaurant rest) {
        List<Menu> menus = rest.getMenu();
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
            intent.setData(Uri.parse("tel:"+detailPhoneNumber.getText())); // pasar telefono del Restaurant   detailPhoneNumber (043) 631641
            Log.d("phone",""+ detailPhoneNumber.getText());
            startActivity(intent);
        }
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int idopcion = item.getItemId();
        switch (idopcion) {
            case R.id.opt2:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"Opcion 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opt1: //Llamar a mapa
                Intent intentMap = new Intent(this,MapsActivity.class);
                intentMap.putExtra(EXTRA_NAME, detailName.getText());
                intentMap.putExtra(EXTRA_LATITUD, detailLatitud.getText());
                intentMap.putExtra(EXTRA_LONGITUD, detailLongitud.getText());
                Log.d("mapArctmr",""+detailLatitud.getText() + detailLongitud.getText());
                startActivity(intentMap);
                break;
            case R.id.opt3:
                llamar();
                break;
        }
        return super.onOptionsItemSelected(item);
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

                Bitmap foto_bitmap = (Bitmap) data.getExtras().get("data");
                Intent intent = new Intent(this,FotosComidaActivity.class);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                foto_bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("fotografia_plato",byteArray);
                startActivityForResult(intent,1984);


               // Bitmap foto = (Bitmap) data.getExtras().get("data");
            }
        } else if (requestCode==1984){
            dialog_comida.dismiss();
            if (resultCode==RESULT_OK) {
/*                restaurantDetail = getRestaurantData(idRest);
                lista_platos = (RecyclerView) findViewById(R.id.lista_platos);
                lista_platos.setLayoutManager(new LinearLayoutManager(this));
                RecyclerView.Adapter adapter_platos = new PlatosAdapter(this, getPlatos(restaurantDetail));
                lista_platos.setAdapter(adapter_platos);*/
            }
        } else if (requestCode== 2222){
            /*Intent intent = new Intent(this,FotosComidaActivity.class);
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Log.d("filPath",filePath[0]+filePath[1]+"");
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap foto_bitmap = (BitmapFactory.decodeFile(picturePath));

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            intent.putExtra("fotografia_plato",byteArray);
            startActivityForResult(intent,1984);*/
        }

    }

        public void seleccionarGaleria(View view) {
            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2222);
    }
   /* public void verMapa(View view) {
        Toast.makeText(this,"VER MAPA",Toast.LENGTH_LONG).show();
    }*/
}

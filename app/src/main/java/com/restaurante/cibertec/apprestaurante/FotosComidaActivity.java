package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FotosComidaActivity extends AppCompatActivity {

    private static final int REQUEST_CAPTURA =111 ;
    private ImageView fotografia_plato;
    private TextView descripcion_plato;
    private Button boton_comida;

//    String currentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_comida2);

        fotografia_plato = (ImageView)findViewById(R.id.fotografia_plato);
        descripcion_plato=(TextView)findViewById(R.id.descripcion_plato);
        Intent intent= getIntent();
        byte [] foto_byte=intent.getByteArrayExtra("fotografia_plato");
        Bitmap foto_bitmap=BitmapFactory.decodeByteArray(foto_byte,0,foto_byte.length);
        fotografia_plato.setImageBitmap(foto_bitmap);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void regresarRest(View view) {
        this.finish();

    }
}

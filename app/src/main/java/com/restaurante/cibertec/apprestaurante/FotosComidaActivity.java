package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantmodel.dao.PlatosDao;
import com.example.restaurantmodel.impl.PlatosDaoImpl;
import com.example.restaurantmodel.model.Platos;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.Date;

public class FotosComidaActivity extends AppCompatActivity {

    private static final int REQUEST_CAPTURA =111 ;
    private ImageView fotografia_plato;
    private EditText descripcion_plato;
    private Button boton_comida;

    byte [] foto_byte;
    SharedPreferences appPreferences;

//    String currentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_comida2);
        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);


        fotografia_plato = (ImageView)findViewById(R.id.fotografia_plato);
        descripcion_plato=(EditText)findViewById(R.id.descripcion_plato);
        Intent intent= getIntent();
        foto_byte=intent.getByteArrayExtra("fotografia_plato");
        Bitmap foto_bitmap=BitmapFactory.decodeByteArray(foto_byte,0,foto_byte.length);
        fotografia_plato.setImageBitmap(foto_bitmap);
    }

    public void regresarRest(View view) {
        //Guardar la imagen en BD
        PlatosDao dao = new PlatosDaoImpl(this);
        Platos plato = new Platos();

        int userid = appPreferences.getInt(getString(R.string.userid),0);
        User user = new User();
        user.setId(userid);
        plato.setUser(user);

        int restid = appPreferences.getInt(getString(R.string.restaurantid),0);
        Restaurant rest = new Restaurant();
        rest.setId(restid);
        plato.setRestaurant(rest);

        plato.setDescription(descripcion_plato.getText().toString());
        plato.setDate(new Date());
        plato.setPhoto(foto_byte);

        int platoid = (int)dao.insertPlato(plato);
        Intent intent = new Intent();
        intent.putExtra("PLATOID",platoid);
        setResult(RESULT_OK,intent);
        this.finish();

    }

       // photo = (ImageView)findViewById(R.id.dishphoto);
    }

//    public void tomarFoto(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_CAPTURA);
//        int permisoWriteExternalCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        Log.d("CAMERAIMG", "CHECK: " + permisoWriteExternalCheck + " granted: " + PackageManager.PERMISSION_GRANTED);
//        if (permisoWriteExternalCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 12);
//        } else {
//            openCamera();
//        }
//    }
//    //DONDE LLEGA CUANDO SE LLAMA EL REQUEST PERMISSIONS
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode==12){
//            Log.d("CAMERAIMG"," perms: "+permissions[0]+" text: "+Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if ( grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                openCamera();
//            }
//        }
//    }
//
//    private void openCamera() {
//        File fileImag = null;
//        try {
//            fileImag = createImageFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImag));
//        startActivityForResult(intent, 123);
//    }
//
//    // SOLO PARA ANDROID 5 o anterior SIN PERMISOS
//    private File createImageFile() throws IOException {
//        String timestamp = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
//        String filename ="REST_"+timestamp+"_";
//        File storageDir = Environment.getExternalStorageDirectory();
//        //File storageDir = Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(filename,".jpg",storageDir);
//
//        currentPath = image.getAbsolutePath();
//        Log.d("CAMERAIMG","CREATE "+currentPath);
//        return image;
//    }







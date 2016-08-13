package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FotosComidaActivity extends AppCompatActivity {

    private static final int REQUEST_CAPTURA =111 ;
    private ImageView photo;

//    String currentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_comida2);

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

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==111){
            if (resultCode==RESULT_OK){
                Bitmap foto = (Bitmap) data.getExtras().get("data");
                photo.setImageBitmap(foto);
            }
        }
    }

    public void tomarFoto(View view) {
    }*/
}

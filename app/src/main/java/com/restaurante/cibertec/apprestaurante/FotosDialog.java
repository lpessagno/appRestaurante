package com.restaurante.cibertec.apprestaurante;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import android.view.View;


import android.widget.ImageView;


/**
 * Created by Jorge on 7/29/2016.
 */
public class FotosDialog extends DialogFragment {

    ImageView photos;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fotos_comida,null);
        builder.setView(view);
        builder.setTitle("Subir Fotos");
      /*  builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("APPResena","Agregar Resena");
                Toast.makeText(getActivity(),"Foto Agregada",Toast.LENGTH_SHORT).show();
                //TRANSFORMAR imagen de PHOTO EN BYTE[]
                //INSERTAR EN BD
            }
        });*/
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //NO hacer nada.
            }
        });


        return builder.create();
    }

}

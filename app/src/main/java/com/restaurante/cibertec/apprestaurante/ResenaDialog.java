package com.restaurante.cibertec.apprestaurante;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jorge on 7/29/2016.
 */
public class ResenaDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_resena,null);
        builder.setView(view);
        builder.setTitle("Dejar Reseña");
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("APPResena","Agregar Resena");
                Toast.makeText(getActivity(),"Resena Agregada",Toast.LENGTH_SHORT).show();
                //Agregar la resena a la lista y recargar la ventana
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //NO hacer nada.
            }
        });

        return builder.create();
    }
}

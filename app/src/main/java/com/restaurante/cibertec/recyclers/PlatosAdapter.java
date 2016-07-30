package com.restaurante.cibertec.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantmodel.model.Platos;
import com.restaurante.cibertec.apprestaurante.R;

import java.util.List;

/**
 * Created by Leslie on 29/07/2016.
 */
public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.CustomPlatosHolder> {
    private Context context;
    private List<Platos> lista_platos;

    public PlatosAdapter(Context context, List<Platos> lista_platos) {
        this.context = context;
        this.lista_platos = lista_platos;
    }

    @Override
    public PlatosAdapter.CustomPlatosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_plato,null);
        CustomPlatosHolder holder= new CustomPlatosHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlatosAdapter.CustomPlatosHolder holder, int position) {
        Platos plato= lista_platos.get(position);
        holder.nombre_plato.setText(plato.getDescription());
        holder.foto_plato.setImageResource(R.mipmap.lomito);
    }

    @Override
    public int getItemCount() {
        return lista_platos== null?0:lista_platos.size();
    }


    class CustomPlatosHolder extends RecyclerView.ViewHolder{
      ImageView foto_plato;
        TextView nombre_plato;
        public CustomPlatosHolder(View itemView) {
            super(itemView);
            foto_plato= (ImageView)itemView.findViewById(R.id.foto_plato);
            nombre_plato=(TextView)itemView.findViewById(R.id.nombre_plato);
            //TODO: agregar un onclik para pop up.
        }
    }
}

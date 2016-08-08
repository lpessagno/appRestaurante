package com.restaurante.cibertec.apprestaurante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 26/03/2016.
 */
public class RestauranteRecyclerAdapter extends RecyclerView.Adapter<RestauranteRecyclerAdapter.RestauranteViewHolder> {

    public static final String IMAGEN = "IMAGEN";
    public static final String NOMBRE = "NOMBRE";
    public static final String UBICACION = "UBICACION";
    public static final String TIPOCOMIDA = "TIPOCOMIDA";
    public static final String DISTANCIA = "DISTANCIA";
    public static final String PRECIO = "PRECIO";
    public static final String RANKING = "RANKING";
    public static final String VOTOS = "VOTOS";
    private Context context;
    private List<Restaurante> data;

    public RestauranteRecyclerAdapter(Context context, List<Restaurante> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RestauranteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("FFFF", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detalle_rest, null);
        RestauranteViewHolder restauranteViewHolder = new RestauranteViewHolder(view);
        return restauranteViewHolder;
    }

    @Override
    public void onBindViewHolder(RestauranteViewHolder holder, int position) {

        Log.d("FFFF", "onBindViewHolder");
        Restaurante fila = data.get(position);
        holder.txtNombre.setText(fila.getNombre());
        holder.txtUbicacion.setText(fila.getUbicacion());
        holder.imgRestaurant.setImageResource(fila.getImagen());
        holder.txtTipoComida.setText(fila.getTipoComida());
        holder.txtUbicacion.setText(fila.getUbicacion());
        holder.txtDistancia.setText(fila.getDistancia());
        holder.txtPrecio.setText("S/." + fila.getPrecio());
        holder.txtRanking.setText("" + fila.getRanking());
        holder.txtVotos.setText("" + fila.getVotos());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class RestauranteViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtNombre;
        protected TextView txtUbicacion;
        protected ImageView imgRestaurant;
        protected TextView txtTipoComida;
        protected TextView txtRanking;
        protected TextView txtVotos;
        protected TextView txtPrecio;
        protected TextView txtDistancia;


        public RestauranteViewHolder(View itemView) {
            super(itemView);
            this.txtNombre = (TextView) itemView.findViewById(R.id.nombreRest);
            this.txtUbicacion = (TextView) itemView.findViewById(R.id.ubicacion);
            this.imgRestaurant = (ImageView) itemView.findViewById(R.id.imagenRest);
            this.txtTipoComida = (TextView) itemView.findViewById(R.id.tipoComida);
            this.txtRanking = (TextView) itemView.findViewById(R.id.ranking);
            this.txtVotos = (TextView) itemView.findViewById(R.id.votos);
            this.txtPrecio = (TextView) itemView.findViewById(R.id.precio);
            this.txtDistancia = (TextView) itemView.findViewById(R.id.distancia);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Restaurante restaurante = data.get(position);
                    Intent intent = new Intent(context, ResturantActivity.class);
                    intent.putExtra(IMAGEN,restaurante.getImagen());
                    intent.putExtra(NOMBRE,restaurante.getNombre());
                    intent.putExtra(UBICACION,restaurante.getUbicacion());
                    intent.putExtra(TIPOCOMIDA,restaurante.getTipoComida());
                    intent.putExtra(DISTANCIA,restaurante.getDistancia());
                    intent.putExtra(PRECIO,restaurante.getPrecio());
                    intent.putExtra(RANKING,restaurante.getRanking());
                    intent.putExtra(VOTOS,restaurante.getVotos());

                    context.startActivity(intent);

                }
            });
        }
    }
}

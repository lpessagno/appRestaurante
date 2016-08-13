package com.restaurante.cibertec.recyclers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmodel.model.Restaurant;
import com.restaurante.cibertec.apprestaurante.R;
import com.restaurante.cibertec.apprestaurante.ResturantActivity;

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
    private List<Restaurant> data;

    public RestauranteRecyclerAdapter(Context context, List<Restaurant> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RestauranteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d("FFFF", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detalle_rest, null);
        RestauranteViewHolder restauranteViewHolder = new RestauranteViewHolder(view);
        return restauranteViewHolder;
    }

    @Override
    public void onBindViewHolder(RestauranteViewHolder holder, int position) {

        //Log.d("FFFF", "onBindViewHolder");
        Restaurant fila = data.get(position);
        holder.txtNombre.setText(fila.getName());
        //holder.imgRestaurant.setImageResource(fila.getPhotoid());
        Bitmap bit = BitmapFactory.decodeByteArray(fila.getPhoto(),0,fila.getPhoto().length);
        holder.imgRestaurant.setImageBitmap(bit);
        //otra forma de setear la imagen
        holder.txtUbicacion.setText(fila.getAddress());
        holder.txtTipoComida.setText(fila.getCategories().get(0).getName());
        holder.txtRanking.setText(String.valueOf(fila.getAvg_ranking()));
        holder.txtVotos.setText(""+fila.getVotos());
        holder.txtPrecio.setText(String.valueOf(fila.getAvg_price()));
        holder.txtDistancia.setText("111mts");
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
                    Restaurant restaurante = data.get(position);
                    Intent intent = new Intent(context, ResturantActivity.class);
                    intent.putExtra(context.getString(R.string.intentIdExtra),restaurante.getId());
                    
                    
                    
//                    intent.putExtra(IMAGEN,restaurante.getImagen());
//                    intent.putExtra(NOMBRE,restaurante.getNombre());
//                    intent.putExtra(UBICACION,restaurante.getUbicacion());
//                    intent.putExtra(TIPOCOMIDA,restaurante.getTipoComida());
//                    intent.putExtra(DISTANCIA,restaurante.getDistancia());
//                    intent.putExtra(PRECIO,restaurante.getPrecio());
//                    intent.putExtra(RANKING,restaurante.getRanking());
//                    intent.putExtra(VOTOS,restaurante.getVotos());

                    context.startActivity(intent);

                }
            });
        }
    }
}

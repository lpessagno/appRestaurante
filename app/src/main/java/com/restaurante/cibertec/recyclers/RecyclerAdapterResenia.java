package com.restaurante.cibertec.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantmodel.model.Commentary;
import com.restaurante.cibertec.apprestaurante.R;

import java.util.List;

/**
 * Created by azapata on 15/07/2016.
 */
public class RecyclerAdapterResenia extends RecyclerView.Adapter<RecyclerAdapterResenia.CustomViewHolder> {

    private Context context;
    private List<Commentary> data;


    public  RecyclerAdapterResenia(Context context, List<Commentary> data){
        this.context = context;
        this.data = data;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowresenia, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Commentary fila = data.get(position);
        holder.txtrestname.setText(fila.getRestaurant().getName());
        holder.txtfecha.setText(fila.getDate().toString());
        holder.txtresenia.setText(fila.getComment());
        //holder.imgres.setImageResource(fila.getImagen());
    }

    @Override
    public int getItemCount() {
       return data==null? 0: data.size();
    }


    public  class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView txtrestname,txtfecha, txtresenia;
        protected ImageView imgres;

        public CustomViewHolder(View itemview) {
            super(itemview);
            this.txtrestname = (TextView) itemview.findViewById(R.id.txtrestname);
            this.txtfecha = (TextView) itemview.findViewById(R.id.txtfecha);
            this.txtresenia = (TextView) itemview.findViewById(R.id.txtresenia);
            this.imgres = (ImageView)itemview.findViewById(R.id.imgres);
        }
    }
}

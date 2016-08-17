package com.restaurante.cibertec.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Platos;
import com.restaurante.cibertec.apprestaurante.R;

import java.util.List;

/**
 * Created by Leslie on 29/07/2016.
 */
public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.CustomComentariosHolder> {

    private Context context;
    private List<Commentary> lista_comentarios;

    public ComentariosAdapter(Context context, List<Commentary> lista_comentarios) {
        this.context = context;
        this.lista_comentarios = lista_comentarios;
    }

    @Override
    public ComentariosAdapter.CustomComentariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comentario,null);
        CustomComentariosHolder holder= new CustomComentariosHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComentariosAdapter.CustomComentariosHolder holder, int position) {
        Commentary comment= lista_comentarios.get(position);
        holder.usuario.setText(comment.getUser().getName());
        holder.comentario.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return lista_comentarios== null?0:lista_comentarios.size();
    }

    public void swapData(List<Commentary> list){
        if (lista_comentarios!=null){
            lista_comentarios.clear();
            lista_comentarios.addAll(list);
        } else {
            lista_comentarios = list;
        }
        notifyDataSetChanged();
    }

    class CustomComentariosHolder extends RecyclerView.ViewHolder {
      TextView comentario;
        TextView usuario;
        public CustomComentariosHolder(View itemView) {
            super(itemView);
            comentario= (TextView)itemView.findViewById(R.id.comentario);
            usuario=(TextView)itemView.findViewById(R.id.usuario);
            //TODO: agregar un onclik para pop up.
        }
    }
}

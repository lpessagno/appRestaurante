package com.restaurante.cibertec.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.restaurantmodel.model.Menu;
import com.restaurante.cibertec.apprestaurante.R;

import java.util.List;

/**
 * Created by Leslie on 29/07/2016.
 */
public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.CustomMenusHolder> {

    private Context context;
    private List<Menu> lista_menus;

    public MenusAdapter(Context context, List<Menu> lista_menus) {
        this.context = context;
        this.lista_menus = lista_menus;
    }

    @Override
    public MenusAdapter.CustomMenusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_menu,null);
        CustomMenusHolder holder= new CustomMenusHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenusAdapter.CustomMenusHolder holder, int position) {
        Menu menu= lista_menus.get(position);
        holder.menu_txt.setText(menu.getName());
        holder.precio.setText(menu.getPrice()+" Soles");
    }

    @Override
    public int getItemCount() {
        return lista_menus== null?0:lista_menus.size();
    }


    class CustomMenusHolder extends RecyclerView.ViewHolder {
      TextView menu_txt;
        TextView precio;
        public CustomMenusHolder(View itemView) {
            super(itemView);
            menu_txt= (TextView)itemView.findViewById(R.id.menu);
            precio=(TextView)itemView.findViewById(R.id.precio);
            //TODO: agregar un onclik para pop up.
        }
    }
}

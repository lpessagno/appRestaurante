package com.example.restaurantmodel.dao;



import com.example.restaurantmodel.model.Favorites;

import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public interface FavoritesDAO {

    public  long insertarFavorites(Favorites favorites);
    public  int actualizarFavorites(Favorites favorites);
    public  int eliminarFavorites(Favorites favorites);
    public  Favorites obtenerFavorites(Favorites favorites);
    public List<Favorites> listarFavorites();
}

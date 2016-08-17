package com.example.restaurantmodel.dao;



import com.example.restaurantmodel.model.Favorites;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public interface FavoritesDAO {

    long insertarFavorites(Favorites favorites);
    int eliminarFavorites(Favorites favorites);
    //public  Favorites obtenerFavorites(Favorites favorites);
    List<Favorites> listarFavorites(int userid);
    Favorites getExistFavorite(User user, Restaurant rest);
}

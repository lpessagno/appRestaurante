package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.Resenia;

import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public interface ReseniaDAO {

    public  long insertarResenia(Resenia resenia);
    public  int actualizarResenia(Resenia resenia);
    public  int eliminarResenia(Resenia resenia);
    public  Resenia obtenerResenia(Resenia resenia);
    public List<Resenia> listarResenia();
}

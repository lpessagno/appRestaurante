package com.example.restaurantmodel.dao;

import com.example.restaurantmodel.model.Commentary;

import java.util.List;

/**
 * Created by azapata on 04/08/2016.
 */
public interface ReseniaDAO {

    public  long insertarResenia(Commentary commentary);
    public  int actualizarResenia(Commentary commentary);
    public  int eliminarResenia(Commentary commentary);
    public Commentary obtenerResenia(Commentary commentary);
    public List<Commentary> listarResenia();
}

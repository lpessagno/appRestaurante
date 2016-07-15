package com.restaurante.cibertec.apprestaurante;

import android.graphics.Bitmap;

/**
 * Created by azapata on 15/07/2016.
 */
public class Resenia {

    String restname;
    String fecha;
    String resenia;
    int imagen;
    Bitmap imagenBitmap;

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenBitmap() {
        return imagenBitmap;
    }

    public void setImagenBitmap(Bitmap imagenBitmap) {
        this.imagenBitmap = imagenBitmap;
    }

    @Override
    public String toString() {
        return restname + " " + resenia;
    }
}

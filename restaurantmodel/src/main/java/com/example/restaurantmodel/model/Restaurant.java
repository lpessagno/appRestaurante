package com.example.restaurantmodel.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 7/24/2016.
 */
public class Restaurant implements Serializable  {

    private int id;
    private String name;
    private String horario;
    private List<Category> categories;
    private List<Menu> menu;
    private double avg_ranking; //en veremos -> calculados
    private double avg_price;   //en veremos -> calculados

    private String email;
    private String phone;
    private String webpage;

    private District district;
    private String address;
    private String latitude;
    private String longitude;

    //Setear correctamente
    private List<Commentary> resena;
    private List<Platos> userPhotos;

    //decidir cual usar
    private byte[] photo;
    private int photoid;

    public Restaurant() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public double getAvg_ranking() {
        return avg_ranking;
    }

    public void setAvg_ranking(double avg_ranking) {
        this.avg_ranking = avg_ranking;
    }

    public double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String telephone) {
        this.phone = telephone;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Commentary> getResena() {
        return resena;
    }

    public void setResena(List<Commentary> resena) {
        this.resena = resena;
    }

    public List<Platos> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<Platos> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public int getPhotoid() {
        return photoid;
    }

    public void setPhotoid(int photoid) {
        this.photoid = photoid;
    }

}

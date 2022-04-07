package com.models;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 07/04/2022.
 */
public class Eventos {
    private int imgEvento;
    private String titulo;
    private double precio;

    public Eventos(String titulo, int imgEvento, double precio) {

        this.titulo = titulo;
        this.imgEvento = imgEvento;
        this.precio = precio;
    }

    public void Eventos(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getImgEvento() {
        return imgEvento;
    }

    public void setImgEvento(int imgEvento) {
        this.imgEvento = imgEvento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

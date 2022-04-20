package com.models;

import java.io.Serializable;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 07/04/2022.
 */
public class Eventos implements Serializable {
    private int imgEvento;
    private String titulo;
    private double precio;
    private String fechaEvento, duracion, descripcion;

    public Eventos(String titulo, int imgEvento, double precio, String fechaEvento, String duracion, String descripcion) {
        this.titulo = titulo;
        this.imgEvento = imgEvento;
        this.precio = precio;
        this.fechaEvento = fechaEvento;
        this.duracion = duracion;
        this.descripcion = descripcion;
    }

    public Eventos(){
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

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

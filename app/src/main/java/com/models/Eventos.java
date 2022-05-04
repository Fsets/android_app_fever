package com.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 07/04/2022.
 */
public class Eventos implements Parcelable {
    private int imgEvento;
    private String titulo;
    private double precio;
    private String fechaEvento, duracion, descripcion;

    //constructor
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
    //getter and setter
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

    //parcelable class
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgEvento);
        dest.writeString(this.titulo);
        dest.writeDouble(this.precio);
        dest.writeString(this.fechaEvento);
        dest.writeString(this.duracion);
        dest.writeString(this.descripcion);
    }

    public void readFromParcel(Parcel source) {
        this.imgEvento = source.readInt();
        this.titulo = source.readString();
        this.precio = source.readDouble();
        this.fechaEvento = source.readString();
        this.duracion = source.readString();
        this.descripcion = source.readString();
    }

    protected Eventos(Parcel in) {
        this.imgEvento = in.readInt();
        this.titulo = in.readString();
        this.precio = in.readDouble();
        this.fechaEvento = in.readString();
        this.duracion = in.readString();
        this.descripcion = in.readString();
    }

    public static final Parcelable.Creator<Eventos> CREATOR = new Parcelable.Creator<Eventos>() {
        @Override
        public Eventos createFromParcel(Parcel source) {
            return new Eventos(source);
        }

        @Override
        public Eventos[] newArray(int size) {
            return new Eventos[size];
        }
    };
}

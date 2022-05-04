package com.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 04/05/2022.
 */
public class Cancion implements Parcelable {
    private int imgCancion;
    private String titulo;
    private double precio;
    private String fechaCancion, descripcion, nombreArtista;

    public Cancion(int imgCancion, String titulo, double precio, String fechaEvento, String descripcion, String nombreArtista) {
        this.imgCancion = imgCancion;
        this.titulo = titulo;
        this.precio = precio;
        this.fechaCancion = fechaEvento;
        this.descripcion = descripcion;
        this.nombreArtista = nombreArtista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgCancion);
        dest.writeString(this.titulo);
        dest.writeDouble(this.precio);
        dest.writeString(this.fechaCancion);
        dest.writeString(this.descripcion);
        dest.writeString(this.nombreArtista);
    }

    public void readFromParcel(Parcel source) {
        this.imgCancion = source.readInt();
        this.titulo = source.readString();
        this.precio = source.readDouble();
        this.fechaCancion = source.readString();
        this.descripcion = source.readString();
        this.nombreArtista = source.readString();
    }

    public Cancion() {
    }

    protected Cancion(Parcel in) {
        this.imgCancion = in.readInt();
        this.titulo = in.readString();
        this.precio = in.readDouble();
        this.fechaCancion = in.readString();
        this.descripcion = in.readString();
        this.nombreArtista = in.readString();
    }

    public static final Parcelable.Creator<Cancion> CREATOR = new Parcelable.Creator<Cancion>() {
        @Override
        public Cancion createFromParcel(Parcel source) {
            return new Cancion(source);
        }

        @Override
        public Cancion[] newArray(int size) {
            return new Cancion[size];
        }
    };

    public int getImgCancion() {
        return imgCancion;
    }

    public void setImgCancion(int imgCancion) {
        this.imgCancion = imgCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechaCancion() {
        return fechaCancion;
    }

    public void setFechaCancion(String fechaEvento) {
        this.fechaCancion = fechaEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }
}

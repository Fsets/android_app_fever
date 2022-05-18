package com.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 18/05/2022.
 */
public class NFTs implements Parcelable {
    private int imgNFT, imgTipo;
    private String NombreNft, Estado;

    public NFTs(int imgNFT, int imgTipo, String nombreNft, String estado) {
        this.imgNFT = imgNFT;
        this.imgTipo = imgTipo;
        NombreNft = nombreNft;
        Estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgNFT);
        dest.writeInt(this.imgTipo);
        dest.writeString(this.NombreNft);
        dest.writeString(this.Estado);
    }

    public void readFromParcel(Parcel source) {
        this.imgNFT = source.readInt();
        this.imgTipo = source.readInt();
        this.NombreNft = source.readString();
        this.Estado = source.readString();
    }

    public NFTs() {
    }

    protected NFTs(Parcel in) {
        this.imgNFT = in.readInt();
        this.imgTipo = in.readInt();
        this.NombreNft = in.readString();
        this.Estado = in.readString();
    }

    public static final Creator<NFTs> CREATOR = new Creator<NFTs>() {
        @Override
        public NFTs createFromParcel(Parcel source) {
            return new NFTs(source);
        }

        @Override
        public NFTs[] newArray(int size) {
            return new NFTs[size];
        }
    };

    public int getImgNFT() {
        return imgNFT;
    }

    public void setImgNFT(int imgNFT) {
        this.imgNFT = imgNFT;
    }

    public int getImgTipo() {
        return imgTipo;
    }

    public void setImgTipo(int imgTipo) {
        this.imgTipo = imgTipo;
    }

    public String getNombreNft() {
        return NombreNft;
    }

    public void setNombreNft(String nombreNft) {
        NombreNft = nombreNft;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public static Creator<NFTs> getCREATOR() {
        return CREATOR;
    }
}

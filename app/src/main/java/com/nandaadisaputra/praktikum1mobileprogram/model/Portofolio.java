package com.nandaadisaputra.praktikum1mobileprogram.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Portofolio implements Parcelable {
    private int gambar;
    private String judul;

    public Portofolio() {
    }

    private Portofolio(Parcel in) {
        gambar = in.readInt();
        judul = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gambar);
        dest.writeString(judul);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Portofolio> CREATOR = new Creator<Portofolio>() {
        @Override
        public Portofolio createFromParcel(Parcel in) {
            return new Portofolio(in);
        }

        @Override
        public Portofolio[] newArray(int size) {
            return new Portofolio[size];
        }
    };

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
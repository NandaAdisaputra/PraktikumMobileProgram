package com.nandaadisaputra.praktikum1mobileprogram.crud.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Biodata implements Parcelable {
    private int id;
    private String nomor;
    private String nama;
    private String tanggal_lahir;
    private String jenis_kelamin;
    private String alamat;
    private String date;

    public Biodata(int id, String nomor, String nama, String tanggal_lahir, String jenis_kelamin, String alamat, String date) {
        this.id = id;
        this.nomor = nomor;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.date = date;
    }

    protected Biodata(Parcel in) {
        id = in.readInt();
        nomor = in.readString();
        nama = in.readString();
        tanggal_lahir = in.readString();
        jenis_kelamin = in.readString();
        alamat = in.readString();
        date = in.readString();
    }

    public Biodata() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomor);
        dest.writeString(nama);
        dest.writeString(tanggal_lahir);
        dest.writeString(jenis_kelamin);
        dest.writeString(alamat);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Biodata> CREATOR = new Creator<Biodata>() {
        @Override
        public Biodata createFromParcel(Parcel in) {
            return new Biodata(in);
        }

        @Override
        public Biodata[] newArray(int size) {
            return new Biodata[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
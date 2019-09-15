package com.nandaadisaputra.praktikum1mobileprogram.model;

public class FormPenjualanModel {
    double jumlahbarang;
    double hargasatuan;

    public FormPenjualanModel() {
    }
    public void save(double jumlahbarang, double hargasatuan) {
        this.jumlahbarang = jumlahbarang;
        this.hargasatuan = hargasatuan;
    }
    public double getHitung() {
        return jumlahbarang * hargasatuan;
    }
    public double getHitung2()
    {
        return jumlahbarang + hargasatuan;
    }
}

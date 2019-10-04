package com.nandaadisaputra.praktikum1mobileprogram.bangundatar.model;

public class BangunDatarModel {
    double angka1;
    double angka2;
    double angka3;

    public BangunDatarModel() {
    }
    public void save(double angka1, double angka2, double angka3) {
        this.angka1 = angka1;
        this.angka2 = angka2;
        this.angka3 = angka3;
    }
    public double getSegitiga() {
        return (angka1 * angka2) / 2;
    }
    public double getPersegi() {
        return (angka1 * angka2);
    }
    public double getTrapesium() {
        return angka3*(angka1 + angka2) /2 ;
    }
    public double getJajarGenjang() {
        return (angka1* angka2);
    }
}

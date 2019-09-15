package com.nandaadisaputra.praktikum1mobileprogram.model;

public class KalkulatorModel {
    double angka1;
    double angka2;

    public KalkulatorModel() {
    }
    public void save(double angka1, double angka2) {
        this.angka1 = angka1;
        this.angka2 = angka2;
    }
    public double getKali() {
        return angka1 * angka2;
    }
    public double getTambah()
    {
        return angka1 + angka2;
    }
    public double getKurang() {
        return angka1 - angka2;
    }
    public double getBagi() {

        return angka1/angka2;
    }
}


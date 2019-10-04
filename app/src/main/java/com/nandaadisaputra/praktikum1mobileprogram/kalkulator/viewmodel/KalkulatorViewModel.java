package com.nandaadisaputra.praktikum1mobileprogram.kalkulator.viewmodel;

import com.nandaadisaputra.praktikum1mobileprogram.kalkulator.model.KalkulatorModel;

public class KalkulatorViewModel {
    private KalkulatorModel kalkulatorModel;
    public KalkulatorViewModel(KalkulatorModel kalkulatorModel) {
        this.kalkulatorModel = kalkulatorModel;
    }
    public void save(double angka1, double angka2) {
        kalkulatorModel.save(angka1, angka2);
    }
    public double getTambah() {
        return kalkulatorModel.getTambah();
    }
    public double getKurang() {
        return kalkulatorModel.getKurang();
    }
    public double getKali() {
        return kalkulatorModel.getKali();
    }
    public double getBagi() { return kalkulatorModel.getBagi();
    }
}
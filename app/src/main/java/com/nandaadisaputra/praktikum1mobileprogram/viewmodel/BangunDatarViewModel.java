package com.nandaadisaputra.praktikum1mobileprogram.viewmodel;

import com.nandaadisaputra.praktikum1mobileprogram.model.BangunDatarModel;

public class BangunDatarViewModel {

        private BangunDatarModel bangunDatarModel;
        public BangunDatarViewModel(BangunDatarModel bangunDatarModel) {
            this.bangunDatarModel = bangunDatarModel;
        }
        public void save(double angka1, double angka2, double angka3) {
            bangunDatarModel.save(angka1, angka2, angka3);
        }
        public double getSegitiga() {
            return bangunDatarModel.getSegitiga();
        }
        public double getPersegi() {
            return bangunDatarModel.getPersegi();
        }
        public double getTrapesium() {
            return bangunDatarModel.getTrapesium();
        }
        public double getJajarGenjang() { return bangunDatarModel.getJajarGenjang();
        }
    }
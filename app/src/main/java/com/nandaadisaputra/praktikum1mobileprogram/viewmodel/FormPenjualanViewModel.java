package com.nandaadisaputra.praktikum1mobileprogram.viewmodel;

public class FormPenjualanViewModel {
    private FormPenjualanViewModel formPenjualanViewModel;
    public FormPenjualanViewModel(FormPenjualanViewModel formPenjualanViewModel) {
        this.formPenjualanViewModel = formPenjualanViewModel;
    }

    public FormPenjualanViewModel() {
    }

    public void save(double jumlahbarang, double hargasatuan) {
        formPenjualanViewModel.save(jumlahbarang, hargasatuan);
    }
    public double getHasil() {
        return formPenjualanViewModel.getHasil();
    }
    public double getHasil2() {
        return formPenjualanViewModel.getHasil2();
    }
    }
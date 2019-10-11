package com.nandaadisaputra.praktikum1mobileprogram.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.viewmodel.FormPenjualanViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPenjualanActivity extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.edt_namabarang)
    EditText edtNamabarang;
    @BindView(R.id.edt_jumlahbarang)
    EditText edtJumlahbarang;
    @BindView(R.id.edt_hargasatuan)
    EditText edtHargasatuan;
    @BindView(R.id.btn_hasil)
    Button btnHasil;
    @BindView(R.id.edt_bayar)
    EditText edtBayar;
    @BindView(R.id.btn_hasil2)
    Button btnHasil2;
    @BindView(R.id.edt_kembalian)
    EditText edtKembalian;
    @BindView(R.id.btn_total)
    TextView btnTotal;
    private FormPenjualanViewModel formPenjualanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_penjualan);
        ButterKnife.bind(this);

        formPenjualanViewModel = new FormPenjualanViewModel(new FormPenjualanViewModel());
        btnHasil.setOnClickListener(v -> {
            double jumlahbarang, hargasatuan, hasil;
            jumlahbarang = Double.valueOf(edtJumlahbarang.getText().toString().trim());
            hargasatuan = Double.valueOf(edtHargasatuan.getText().toString().trim());

            hasil = jumlahbarang * hargasatuan;
            String hasil1 = String.valueOf(hasil);
            btnTotal.setText(hasil1);
        });
        btnHasil2.setOnClickListener(v -> {
            double bayar, totalharga1, hasil2;
            totalharga1 = Double.valueOf(btnTotal.getText().toString().trim());
            bayar = Double.valueOf(edtBayar.getText().toString().trim());


            hasil2 = bayar - totalharga1;
            String hasil3 = String.valueOf(hasil2);
            edtKembalian.setText(hasil3);
        });
    }
    @Override
    public void onClick(View v) {
        String a = edtJumlahbarang.getText().toString().trim();
        String b = edtHargasatuan.getText().toString().trim();
        if (TextUtils.isEmpty(a)) {
            edtJumlahbarang.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(b)) {
            edtHargasatuan.setError("Field ini tidak boleh kosong");
        } else {
            double l = Double.parseDouble(a);
            double w = Double.parseDouble(b);
            if (v.getId() == R.id.btn_save) {
                formPenjualanViewModel.save(l, w);
                visible();
            } else if (v.getId() == R.id.btn_hasil) {
                btnHasil.setText(String.valueOf(formPenjualanViewModel.getHasil()));
                gone();
            } else if (v.getId() == R.id.btn_hasil2){
                btnHasil2.setText(String.valueOf(formPenjualanViewModel.getHasil2()));
                gone();
            }
        }
    }
    void visible() {
        btnHasil.setVisibility(View.VISIBLE);
        btnHasil2.setVisibility(View.VISIBLE);
    }

    void gone() {
        btnHasil.setVisibility(View.GONE);
        btnHasil2.setVisibility(View.GONE);
    }
}
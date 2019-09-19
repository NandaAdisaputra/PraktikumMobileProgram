package com.nandaadisaputra.praktikum1mobileprogram.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.model.KalkulatorModel;
import com.nandaadisaputra.praktikum1mobileprogram.viewmodel.KalkulatorViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KalkulatorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edt_angka1)
    EditText edtLength;
    @BindView(R.id.edt_angka2)
    EditText edtWidth;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_tambah)
    Button btnTambah;
    @BindView(R.id.btn_kurang)
    Button btnKurang;
    @BindView(R.id.btn_kali)
    Button btnKali;
    @BindView(R.id.btn_bagi)
    Button btnBagi;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.backkalkulator)
    TextView backkalkulator;
    private KalkulatorViewModel kalkulatorViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        ButterKnife.bind(this);

        kalkulatorViewModel = new KalkulatorViewModel(new KalkulatorModel());
        btnSave.setOnClickListener(this);
        btnTambah.setOnClickListener(this);
        btnKurang.setOnClickListener(this);
        btnKali.setOnClickListener(this);
        btnBagi.setOnClickListener(this);
        backkalkulator.setOnClickListener(view -> {
            Intent intent = new Intent(KalkulatorActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        String length = edtLength.getText().toString().trim();
        String width = edtWidth.getText().toString().trim();
        if (TextUtils.isEmpty(length)) {
            edtLength.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtWidth.setError("Field ini tidak boleh kosong");
            Toast.makeText(this, "Maaf Field ini harus diisi", Toast.LENGTH_LONG).show();
        } else {
            double l = Double.parseDouble(length);
            double w = Double.parseDouble(width);
            if (v.getId() == R.id.btn_save) {
                kalkulatorViewModel.save(l, w);
                visible();
            } else if (v.getId() == R.id.btn_tambah) {
                tvResult.setText(String.valueOf(kalkulatorViewModel.getTambah()));
                gone();
            } else if (v.getId() == R.id.btn_kurang) {
                tvResult.setText(String.valueOf(kalkulatorViewModel.getKurang()));
                gone();
            } else if (v.getId() == R.id.btn_kali) {
                tvResult.setText(String.valueOf(kalkulatorViewModel.getKali()));
                gone();
            } else if (v.getId() == R.id.btn_bagi) {
                tvResult.setText(String.valueOf(kalkulatorViewModel.getBagi()));
                gone();
            }
        }
    }

    void visible() {
        btnTambah.setVisibility(View.VISIBLE);
        btnKurang.setVisibility(View.VISIBLE);
        btnKali.setVisibility(View.VISIBLE);
        btnBagi.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    void gone() {
        btnTambah.setVisibility(View.GONE);
        btnKurang.setVisibility(View.GONE);
        btnKali.setVisibility(View.GONE);
        btnBagi.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }
}
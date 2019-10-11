package com.nandaadisaputra.praktikum1mobileprogram.bangundatar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.MainActivity;
import com.nandaadisaputra.praktikum1mobileprogram.bangundatar.model.BangunDatarModel;
import com.nandaadisaputra.praktikum1mobileprogram.bangundatar.viewmodel.BangunDatarViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BangunDatarActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edt_angka1)
    EditText edtAngka1;
    @BindView(R.id.edt_angka2)
    EditText edtAngka2;
    @BindView(R.id.edt_angka3)
    EditText edtAngka3;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_persegi)
    Button btnPersegi;
    @BindView(R.id.btn_segitiga)
    Button btnSegitiga;
    @BindView(R.id.btn_trapesium)
    Button btnTrapesium;
    @BindView(R.id.btn_jajargenjang)
    Button btnJajargenjang;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.hitung)
    LinearLayout hitung;
    @BindView(R.id.angka1)
    TextView angka1;
    @BindView(R.id.angka2)
    TextView angka2;
    @BindView(R.id.angka3)
    TextView angka3;
    @BindView(R.id.image)
    LottieAnimationView image;
    @BindView(R.id.backbangundatar)
    TextView backbangundatar;
    private BangunDatarViewModel bangunDatarViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangun_datar);
        ButterKnife.bind(this);

        backbangundatar.setOnClickListener(v -> {
            Intent back = new Intent(BangunDatarActivity.this, MainActivity.class);
            startActivity(back);

        });

        bangunDatarViewModel = new BangunDatarViewModel(new BangunDatarModel());
        btnSave.setOnClickListener(this);
        btnSegitiga.setOnClickListener(this);
        btnPersegi.setOnClickListener(this);
        btnTrapesium.setOnClickListener(this);
        btnJajargenjang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String length = edtAngka1.getText().toString().trim();
        String width = edtAngka2.getText().toString().trim();
        String height = edtAngka3.getText().toString().trim();
        if (TextUtils.isEmpty(length)) {
            edtAngka1.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtAngka2.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtAngka3.setError("Field ini tidak boleh kosong");
        } else {
            double l = Double.parseDouble(length);
            double w = Double.parseDouble(width);
            double h = Double.parseDouble(width);
            if (v.getId() == R.id.btn_save) {
                bangunDatarViewModel.save(l, w, h);
                visible();
            } else if (v.getId() == R.id.btn_segitiga) {
                tvResult.setText(String.valueOf(bangunDatarViewModel.getSegitiga()));
                gone();
            } else if (v.getId() == R.id.btn_persegi) {
                tvResult.setText(String.valueOf(bangunDatarViewModel.getPersegi()));
                gone();
            } else if (v.getId() == R.id.btn_trapesium) {
                tvResult.setText(String.valueOf(bangunDatarViewModel.getTrapesium()));
                gone();
            } else if (v.getId() == R.id.btn_jajargenjang) {
                tvResult.setText(String.valueOf(bangunDatarViewModel.getJajarGenjang()));
                gone();
            }
        }
    }

    void visible() {
        btnSegitiga.setVisibility(View.VISIBLE);
        btnPersegi.setVisibility(View.VISIBLE);
        btnTrapesium.setVisibility(View.VISIBLE);
        btnJajargenjang.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    void gone() {
        btnSegitiga.setVisibility(View.GONE);
        btnPersegi.setVisibility(View.GONE);
        btnTrapesium.setVisibility(View.GONE);
        btnTrapesium.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }
}

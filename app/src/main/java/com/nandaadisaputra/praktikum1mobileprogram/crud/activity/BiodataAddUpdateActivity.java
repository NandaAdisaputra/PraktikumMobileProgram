package com.nandaadisaputra.praktikum1mobileprogram.crud.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.MainActivity;
import com.nandaadisaputra.praktikum1mobileprogram.crud.database.BiodataHelper;
import com.nandaadisaputra.praktikum1mobileprogram.crud.entity.Biodata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.ALAMAT;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.DATE;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.JENIS_KELAMIN;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.NAMA;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.NOMOR;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.TANGGAL_LAHIR;

public class BiodataAddUpdateActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.edt_nomor)
    EditText edtNomor;
    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_tanggalLahir)
    EditText edtTanggalLahir;
    @BindView(R.id.edt_jenisKelamin)
    EditText edtJenisKelamin;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    @BindView(R.id.btn_back)
    Button btnBack;
    private boolean isEdit = false;
    private Biodata biodata;
    private int position;
    private BiodataHelper biodataHelper;

    public static final String EXTRA_BIODATA = "extra_biodata";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_add_update);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(v -> {
            Intent back = new Intent(BiodataAddUpdateActivity.this, MainActivity.class);
            startActivity(back);
        });


        biodataHelper = BiodataHelper.getInstance(getApplicationContext());

        biodata = getIntent().getParcelableExtra(EXTRA_BIODATA);
        if (biodata != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
        } else {
            biodata = new Biodata();
        }

        String actionBarSimpan;
        String buttonSimpan;

        if (isEdit) {
            actionBarSimpan = "Ubah";
            buttonSimpan = "Update";

            if (biodata != null) {
                edtNomor.setText(biodata.getNomor());
                edtNama.setText(biodata.getNama());
                edtTanggalLahir.setText(biodata.getTanggal_lahir());
                edtJenisKelamin.setText(biodata.getJenis_kelamin());
                edtAlamat.setText(biodata.getAlamat());
            }
        } else {
            actionBarSimpan = "Tambah";
            buttonSimpan = "Simpan";
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarSimpan);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSimpan.setText(buttonSimpan);

        btnSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_simpan) {
            String nomor = edtNomor.getText().toString().trim();
            String nama = edtNama.getText().toString().trim();
            String tanggal_lahir = edtTanggalLahir.getText().toString().trim();
            String jenis_kelamin = edtJenisKelamin.getText().toString().trim();
            String alamat = edtAlamat.getText().toString().trim();
            /*
            Jika fieldnya masih kosong maka tampilkan error
             */
            if (TextUtils.isEmpty(nomor)) {
                edtNomor.setError("Field can not be blank");
                return;
            }

            biodata.setNomor(nomor);
            biodata.setNama(nama);
            biodata.setTanggal_lahir(tanggal_lahir);
            biodata.setJenis_kelamin(jenis_kelamin);
            biodata.setAlamat(alamat);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_BIODATA, biodata);
            intent.putExtra(EXTRA_POSITION, position);

            // Gunakan contentvalues untuk menampung data
            ContentValues values = new ContentValues();
            values.put(NOMOR, nomor);
            values.put(NAMA, nama);
            values.put(TANGGAL_LAHIR, tanggal_lahir);
            values.put(JENIS_KELAMIN, jenis_kelamin);
            values.put(ALAMAT, alamat);

            /*
            Jika merupakan edit maka setresultnya UPDATE, dan jika bukan maka setresultnya ADD
            */
            if (isEdit) {
                long result = biodataHelper.update(String.valueOf(biodata.getId()), values);
                if (result > 0) {
                    setResult(RESULT_UPDATE, intent);
                    finish();
                } else {
                    Toast.makeText(BiodataAddUpdateActivity.this, "Gagal mengupdate data", Toast.LENGTH_SHORT).show();
                }
            } else {
                biodata.setDate(getCurrentDate());
                values.put(DATE, getCurrentDate());
                long result = biodataHelper.insert(values);

                if (result > 0) {
                    biodata.setId((int) result);
                    setResult(RESULT_ADD, intent);
                    finish();
                } else {
                    Toast.makeText(BiodataAddUpdateActivity.this, "Gagal menambah data", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit) {
            getMenuInflater().inflate(R.menu.menu_form, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    /*
    Konfirmasi dialog sebelum proses batal atau hapus
    close = 10
    deleteBiodata = 20
     */
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Biodata";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, id) -> {
                    if (isDialogClose) {
                        finish();
                    } else {
                        long result = biodataHelper.deleteById(String.valueOf(biodata.getId()));
                        if (result > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        } else {
                            Toast.makeText(BiodataAddUpdateActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
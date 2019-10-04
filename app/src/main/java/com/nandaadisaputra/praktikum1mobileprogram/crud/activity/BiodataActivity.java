package com.nandaadisaputra.praktikum1mobileprogram.crud.activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.crud.adapter.BiodataAdapter;
import com.nandaadisaputra.praktikum1mobileprogram.crud.database.BiodataHelper;
import com.nandaadisaputra.praktikum1mobileprogram.crud.entity.Biodata;
import com.nandaadisaputra.praktikum1mobileprogram.crud.helper.MappingHelper;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BiodataActivity extends AppCompatActivity implements LoadBiodatasCallback {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.rv_biodatas)
    RecyclerView rvBiodatas;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    private BiodataAdapter adapter;
    private BiodataHelper noteHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Biodatas");

        rvBiodatas.setLayoutManager(new LinearLayoutManager(this));
        rvBiodatas.setHasFixedSize(true);
        adapter = new BiodataAdapter(this);
        rvBiodatas.setAdapter(adapter);

        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(BiodataActivity.this, BiodataAddUpdateActivity.class);
            startActivityForResult(intent, BiodataAddUpdateActivity.REQUEST_ADD);
        });

        noteHelper = BiodataHelper.getInstance(getApplicationContext());
        noteHelper.open();

        /*
        Cek jika savedInstaceState null makan akan melakukan proses asynctask nya
        jika tidak,akan mengambil arraylist nya dari yang sudah di simpan
         */
        if (savedInstanceState == null) {
            new LoadBiodataAsync(noteHelper, this).execute();
        } else {
            ArrayList<Biodata> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListBiodatas(list);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListBiodatas());
    }

    @Override
    public void preExecute() {
        /*
        Callback yang akan dipanggil di onPreExecute Asyntask
        Memunculkan progressbar
        */
        runOnUiThread(() -> progressbar.setVisibility(View.VISIBLE));
    }

    @Override
    public void postExecute(ArrayList<Biodata> biodata) {
        /*
        Callback yang akan dipanggil di onPostExture Asynctask
        Menyembunyikan progressbar, kemudian isi adapter dengan data yang ada
         */
        progressbar.setVisibility(View.INVISIBLE);
        if (biodata.size() > 0) {
            adapter.setListBiodatas(biodata);
        } else {
            adapter.setListBiodatas(new ArrayList<Biodata>());
            showSnackbarMessage("Tidak ada data saat ini");
        }
    }

    private static class LoadBiodataAsync extends AsyncTask<Void, Void, ArrayList<Biodata>> {

        private final WeakReference<BiodataHelper> weakBiodataHelper;
        private final WeakReference<LoadBiodatasCallback> weakCallback;

        private LoadBiodataAsync(BiodataHelper biodataHelper, LoadBiodatasCallback callback) {
            weakBiodataHelper = new WeakReference<>(biodataHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Biodata> doInBackground(Void... voids) {
            Cursor dataCursor = weakBiodataHelper.get().queryAll();
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<Biodata> biodata) {
            super.onPostExecute(biodata);

            weakCallback.get().postExecute(biodata);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            // Akan dipanggil jika request codenya ADD
            if (requestCode == BiodataAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == BiodataAddUpdateActivity.RESULT_ADD) {
                    Biodata biodata = data.getParcelableExtra(BiodataAddUpdateActivity.EXTRA_BIODATA);

                    adapter.addItem(biodata);
                    rvBiodatas.smoothScrollToPosition(adapter.getItemCount() - 1);

                    showSnackbarMessage("Satu item berhasil ditambahkan");
                }
            }
            // Update dan Delete memiliki request code sama akan tetapi result codenya berbeda
            else if (requestCode == BiodataAddUpdateActivity.REQUEST_UPDATE) {
                /*
                Akan dipanggil jika result codenya  UPDATE
                Semua data di load kembali dari awal
                */
                if (resultCode == BiodataAddUpdateActivity.RESULT_UPDATE) {

                    Biodata biodata = data.getParcelableExtra(BiodataAddUpdateActivity.EXTRA_BIODATA);
                    int position = data.getIntExtra(BiodataAddUpdateActivity.EXTRA_POSITION, 0);

                    adapter.updateItem(position, biodata);
                    rvBiodatas.smoothScrollToPosition(position);

                    showSnackbarMessage("Satu item berhasil diubah");
                }
                /*
                Akan dipanggil jika result codenya DELETE
                Delete akan menghapus data dari list berdasarkan dari position
                */
                else if (resultCode == BiodataAddUpdateActivity.RESULT_DELETE) {
                    int position = data.getIntExtra(BiodataAddUpdateActivity.EXTRA_POSITION, 0);

                    adapter.removeItem(position);

                    showSnackbarMessage("Satu item berhasil dihapus");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteHelper.close();
    }

    /**
     * Tampilkan snackbar
     *
     * @param message inputan message
     */
    private void showSnackbarMessage(String message) {
        Snackbar.make(rvBiodatas, message, Snackbar.LENGTH_SHORT).show();
    }
}

interface LoadBiodatasCallback {
    void preExecute();

    void postExecute(ArrayList<Biodata> biodata);
}
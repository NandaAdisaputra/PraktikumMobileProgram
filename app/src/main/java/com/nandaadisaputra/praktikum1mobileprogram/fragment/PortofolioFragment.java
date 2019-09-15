package com.nandaadisaputra.praktikum1mobileprogram.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.KalkulatorActivity;
import com.nandaadisaputra.praktikum1mobileprogram.adapter.PortofolioAdapter;
import com.nandaadisaputra.praktikum1mobileprogram.model.Portofolio;
import com.nandaadisaputra.praktikum1mobileprogram.presenter.CollectionInterfacePortofolio;

import java.util.ArrayList;

public class PortofolioFragment extends Fragment implements CollectionInterfacePortofolio {
    //Deklarasi Variable
    private TypedArray dataGambar;
    private String[] dataJudul;
    private String[] dataDeskripsi;
    private String[] dataTanggal;
    private String[] dataGenre;
    //Inisialisasi data yang akan digunakan
    private ArrayList<Portofolio> nportofolio;
    private PortofolioAdapter adapter;
    //deklarasi variabel reyclerview
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.portofolio_fragment, container, false);
        prepare();
        addItem();
        //menampilkan reyclerview yang ada pada file layout dengan id n_movie
        mRecyclerView = rootView.findViewById(R.id.n_portofolio);
        adapter = new PortofolioAdapter(nportofolio, getActivity(), this);
        //menset setukuran
        //menset layoutmanager dan menampilkan daftar/list
        //dalam bentuk linearlayoutmanager pada class saat ini
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        //membuat adapter baru untuk reyclerview
        mRecyclerView.setAdapter(adapter);
        //menset nilai dari adapter
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    private void addItem() {
        //Inisialisasi ArrayList
        nportofolio = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            Portofolio portofolio = new Portofolio();
            portofolio.setGambar(dataGambar.getResourceId(i, -1));
            portofolio.setJudul(dataJudul[i]);
            nportofolio.add(portofolio);
        }
    }


    private void prepare() {
        //Ambil data picture dari array data gambar di Strings
        dataGambar = getResources().obtainTypedArray(R.array.data_gambar);
        //Ambil data text dari array di Strings
        dataJudul = getResources().getStringArray(R.array.data_judul);
    }

    //intent dengan interface
    @Override
    public void intentToDetail(Portofolio portofolio) {
        Intent intent = new Intent(getActivity(), KalkulatorActivity.class);
        intent.putExtra("model", portofolio);
        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
    }
}
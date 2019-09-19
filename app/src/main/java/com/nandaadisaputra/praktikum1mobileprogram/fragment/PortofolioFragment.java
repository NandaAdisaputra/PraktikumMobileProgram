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
    private TypedArray dataGambar;
    private String[] dataNama;
    private ArrayList<Portofolio> nportofolio;
    private PortofolioAdapter adapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.portofolio_fragment, container, false);
        prepare();
        addItem();
        mRecyclerView = rootView.findViewById(R.id.n_portofolio);
        adapter = new PortofolioAdapter(nportofolio, getActivity(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    private void addItem() {
        nportofolio= new ArrayList<>();

        for (int i = 0; i < dataNama.length; i++) {
            Portofolio portofolio = new Portofolio();
            portofolio.setGambar(dataGambar.getResourceId(i, -1));
            portofolio.setJudul(dataNama[i]);
            nportofolio.add(portofolio);
        }
    }


    private void prepare() {
        dataGambar = getResources().obtainTypedArray(R.array.data_gambar);
        dataNama = getResources().getStringArray(R.array.data_judul);
    }

    @Override
    public void intentToDetail(Portofolio portofolio) {
        Intent intent = new Intent(getActivity(), KalkulatorActivity.class);
        intent.putExtra("model", portofolio);
        startActivity(intent);
    }

}
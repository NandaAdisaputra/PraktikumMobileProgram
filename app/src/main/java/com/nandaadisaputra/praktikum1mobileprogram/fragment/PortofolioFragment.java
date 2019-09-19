package com.nandaadisaputra.praktikum1mobileprogram.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.KalkulatorActivity;
import com.nandaadisaputra.praktikum1mobileprogram.adapter.PortofolioAdapter;
import com.nandaadisaputra.praktikum1mobileprogram.model.Portofolio;
import com.nandaadisaputra.praktikum1mobileprogram.presenter.CollectionInterfacePortofolio;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

public class PortofolioFragment extends Fragment implements CollectionInterfacePortofolio,SearchView.OnQueryTextListener{
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.n_portofolio)
    RecyclerView nPortofolio;
    private TypedArray dataGambar;
    private String[] dataJudul;
    private ArrayList<Portofolio> nportofolio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.portofolio_fragment, container, false);
        prepare();
        addItem();
        PortofolioAdapter adapter = new PortofolioAdapter(nportofolio, getActivity(), this);
        nPortofolio.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        nPortofolio.setAdapter(adapter);
        nPortofolio.setHasFixedSize(true);

        return rootView;
    }

    private void addItem() {
        nportofolio = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            Portofolio portofolio = new Portofolio();
            portofolio.setGambar(dataGambar.getResourceId(i, -1));
            portofolio.setJudul(dataJudul[i]);
            nportofolio.add(portofolio);
        }
    }

    private void prepare() {
        dataGambar = getResources().obtainTypedArray(R.array.data_gambar);
        dataJudul = getResources().getStringArray(R.array.data_judul);
    }

    @Override
    public void intentToDetail(Portofolio portofolio) {
        Intent intent = new Intent(getActivity(), KalkulatorActivity.class);
        intent.putExtra("model", portofolio);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public interface OnFragmentInteractionListener {
    }
}
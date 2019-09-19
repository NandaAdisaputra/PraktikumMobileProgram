package com.nandaadisaputra.praktikum1mobileprogram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.fragment.PortofolioFragment;
import com.nandaadisaputra.praktikum1mobileprogram.model.Portofolio;
import com.nandaadisaputra.praktikum1mobileprogram.presenter.CollectionInterfacePortofolio;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PortofolioAdapter extends RecyclerView.Adapter<PortofolioAdapter.ViewHolder> {
    private ArrayList<Portofolio> list;
    private Context context;
    private View.OnClickListener listener;
    private View view;
    private CollectionInterfacePortofolio collectionInterfacePortofolio;


    public PortofolioAdapter(ArrayList<Portofolio> list, Context context, PortofolioFragment collectionInterfacePortofolio) {
        this.list = list;
        this.context = context;
        this.collectionInterfacePortofolio = collectionInterfacePortofolio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_portofolio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvJudul.setText(list.get(position).getJudul());
        Glide.with(context).load(list.get(position).getGambar()).into(holder.ivGambar);
    }

    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_gambar)
        ImageView ivGambar;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        private final Unbinder unbinder;
        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            //onclick
            itemView.setOnClickListener(view -> collectionInterfacePortofolio.intentToDetail(list.get(getAdapterPosition())));
        }
    }
}
package com.nandaadisaputra.praktikum1mobileprogram.crud.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.crud.CustomOnItemClickListener;
import com.nandaadisaputra.praktikum1mobileprogram.crud.activity.BiodataAddUpdateActivity;
import com.nandaadisaputra.praktikum1mobileprogram.crud.entity.Biodata;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.NoteViewHolder> {
    private final ArrayList<Biodata> listBiodatas = new ArrayList<>();
    private final Activity activity;

    public BiodataAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Biodata> getListBiodatas() {
        return listBiodatas;
    }

    public void setListBiodatas(ArrayList<Biodata> listBiodatas) {

        if (listBiodatas.size() > 0) {
            this.listBiodatas.clear();
        }
        this.listBiodatas.addAll(listBiodatas);

        notifyDataSetChanged();
    }

    public void addItem(Biodata biodata) {
        this.listBiodatas.add(biodata);
        notifyItemInserted(listBiodatas.size() - 1);
    }

    public void updateItem(int position, Biodata biodata) {
        this.listBiodatas.set(position, biodata);
        notifyItemChanged(position, biodata);
    }

    public void removeItem(int position) {
        this.listBiodatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listBiodatas.size());
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biodata, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tvItemNomor.setText(listBiodatas.get(position).getNomor());
        holder.tvItemDate.setText(listBiodatas.get(position).getDate());
        holder.tvItemNama.setText(listBiodatas.get(position).getNama());
        holder.tvItemTanggalLahir.setText(listBiodatas.get(position).getTanggal_lahir());
        holder.tvItemJenisKelamin.setText(listBiodatas.get(position).getJenis_kelamin());
        holder.tvItemAlamat.setText(listBiodatas.get(position).getAlamat());
        holder.cvItemNote.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Intent intent = new Intent(activity, BiodataAddUpdateActivity.class);
            intent.putExtra(BiodataAddUpdateActivity.EXTRA_POSITION, position1);
            intent.putExtra(BiodataAddUpdateActivity.EXTRA_BIODATA, listBiodatas.get(position1));
            activity.startActivityForResult(intent, BiodataAddUpdateActivity.REQUEST_UPDATE);
        }));
    }

    @Override
    public int getItemCount() {
        return listBiodatas.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_date)
        TextView tvItemDate;
        @BindView(R.id.tv_item_nomor)
        TextView tvItemNomor;
        @BindView(R.id.tv_item_nama)
        TextView tvItemNama;
        @BindView(R.id.tv_item_tanggalLahir)
        TextView tvItemTanggalLahir;
        @BindView(R.id.tv_item_jenisKelamin)
        TextView tvItemJenisKelamin;
        @BindView(R.id.tv_item_alamat)
        TextView tvItemAlamat;
        @BindView(R.id.cv_item_note)
        CardView cvItemNote;
        private final Unbinder unbinder;

        NoteViewHolder(@NonNull final View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
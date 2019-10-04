package com.nandaadisaputra.praktikum1mobileprogram.crud.helper;

import android.database.Cursor;

import com.nandaadisaputra.praktikum1mobileprogram.crud.entity.Biodata;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.ALAMAT;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.DATE;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.JENIS_KELAMIN;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.NAMA;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.NOMOR;
import static com.nandaadisaputra.praktikum1mobileprogram.crud.database.DatabaseContract.NoteColumns.TANGGAL_LAHIR;

public class MappingHelper {

    public static ArrayList<Biodata> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<Biodata> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String nomor = notesCursor.getString(notesCursor.getColumnIndexOrThrow(NOMOR));
            String nama = notesCursor.getString(notesCursor.getColumnIndexOrThrow(NAMA));
            String tanggal_lahir = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TANGGAL_LAHIR));
            String jenis_kelamin = notesCursor.getString(notesCursor.getColumnIndexOrThrow(JENIS_KELAMIN));
            String alamat = notesCursor.getString(notesCursor.getColumnIndexOrThrow(ALAMAT));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE));
            notesList.add(new Biodata());
        }

        return notesList;
    }
}
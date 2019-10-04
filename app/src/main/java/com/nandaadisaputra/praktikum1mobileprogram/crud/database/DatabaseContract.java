package com.nandaadisaputra.praktikum1mobileprogram.crud.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class NoteColumns implements BaseColumns {
        public static final String TABLE_NAME = "biodata";

        //Biodata nomor
        public static final String NOMOR = "nomor";
        //Biodata nama
        public static final String NAMA = "nama";
        //Biodata tanggal_lahir
        public static final String TANGGAL_LAHIR = "tanggal_lahir";
        //Biodata jenis_kelamin
        public static final String JENIS_KELAMIN = "jenis_kelamin";
        //Biodata alamat
        public static final String ALAMAT = "alamat";
        //Biodata date
        public static final String DATE = "date";

    }
}
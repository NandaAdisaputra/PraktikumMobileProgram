package com.nandaadisaputra.praktikum1mobileprogram.crud.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class NoteColumns implements BaseColumns {
        public static final String TABLE_NAME = "note";

        //Note title
        public static final String TITLE = "title";
        //Note description
        public static final String DESCRIPTION = "description";
        //Note date
        public static final String DATE = "date";

    }
}
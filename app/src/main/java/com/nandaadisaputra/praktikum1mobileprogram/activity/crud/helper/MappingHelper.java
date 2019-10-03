package com.nandaadisaputra.praktikum1mobileprogram.activity.crud.helper;

import android.database.Cursor;
import com.nandaadisaputra.praktikum1mobileprogram.activity.crud.entity.Note;
import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static com.nandaadisaputra.praktikum1mobileprogram.activity.crud.database.DatabaseContract.NoteColumns.DATE;
import static com.nandaadisaputra.praktikum1mobileprogram.activity.crud.database.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.nandaadisaputra.praktikum1mobileprogram.activity.crud.database.DatabaseContract.NoteColumns.TITLE;

public class MappingHelper {

    public static ArrayList<Note> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<Note> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE));
            notesList.add(new Note());
        }

        return notesList;
    }
}
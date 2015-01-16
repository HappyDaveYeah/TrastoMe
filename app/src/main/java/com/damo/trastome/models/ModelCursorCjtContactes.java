package com.damo.trastome.models;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.damo.trastome.dao.Prestec;
import com.damo.trastome.db.DBContracts;

public class ModelCursorCjtContactes {
    private static final String LOG_TAG = "ModelCjtContactes";
    private Cursor dades;

    public ModelCursorCjtContactes(Context context) {
        carregaDades(context);
    }

    private void carregaDades(Context context) {
        String[] projection = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME};

        dades = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    public Cursor getDades() {
        return dades;
    }

    public int getPosition(long id) {
        if (dades.moveToFirst()) {
            do {
                if (id == dades.getLong(dades.getColumnIndex(ContactsContract.Data._ID))) {
                    return dades.getPosition();
                }
            } while (dades.moveToNext());
        }
        return -1;
    }
}
package com.damo.trastome.models;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

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
}
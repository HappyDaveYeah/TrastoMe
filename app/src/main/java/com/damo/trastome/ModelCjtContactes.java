package com.damo.trastome;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

public class ModelCjtContactes {
    private static final String LOG_TAG = "ModelCjtContactes";
    private final DBHelper dbHelper;
    private Cursor dades;

    ModelCjtContactes(Context context) {
        dbHelper = DBHelper.getInstance(context);
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
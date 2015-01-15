package com.damo.trastome.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.damo.trastome.DBContracts;
import com.damo.trastome.DBHelper;

public class ModelCjtItems {
    private static final String LOG_TAG = "ModelCjtItems";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtItems(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades(context);
    }

    private void carregaDades(Context context) {
        dbHelper = DBHelper.getInstance(context.getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = db.query(DBContracts.Item.TABLE_NAME, DBContracts.Item.COLUMNS, null,
                null, null, null, DBContracts.Item.NOM + " ASC", null);
    }

    public Cursor getDades() {
        return dades;
    }
}
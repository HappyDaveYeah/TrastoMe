package com.damo.trastome;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by HappyDave on 07/01/2015.
 */
public class ModelCjtPrestecs {
    private static final String LOG_TAG = "ModelCjtPrestecs";
    private final DBHelper dbHelper;
    private Cursor dades;

    ModelCjtPrestecs(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades();
    }

    private void carregaDades() {
        //TODO: Esborrar aquesta part
        /*SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(DBContracts.Item.DELETE);
        db.execSQL(DBContracts.Contacte.DELETE);
        db.execSQL(DBContracts.Prestec.DELETE);
        dbHelper.onCreate(db);
        db.close();*/

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = db.query(DBContracts.Prestec.TABLE_NAME, DBContracts.Prestec.COLUMNS, null,
                null, null, null, DBContracts.Prestec.DATA + " ASC", null);
    }

    public void add(Prestec prestec) {
        //TODO: add prestec
    }

    public void del(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int x = db.delete(DBContracts.Prestec.TABLE_NAME, DBContracts.Prestec._ID + "=?",
                new String[]{ String.valueOf(id) });
        Log.d(LOG_TAG, "del -> numdeleted = " + x);
        carregaDades();
    }

    public int size() {
        return dades.getCount();
    }

    public Cursor getDades() {
        return dades;
    }
}
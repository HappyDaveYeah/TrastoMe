package com.damo.trastome.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.damo.trastome.dao.Prestec;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.db.DBHelper;

public class ModelCjtPrestecs {
    private static final String LOG_TAG = "ModelCjtPrestecs";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtPrestecs(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades();
    }

    private void carregaDades() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = db.query(DBContracts.Prestec.TABLE_NAME, DBContracts.Prestec.COLUMNS, null,
                null, null, null, DBContracts.Prestec.DATA + " ASC", null);
    }

    public void add(Prestec prestec) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContracts.Prestec.ID_CONTACTE, prestec.getIdContacte());
        values.put(DBContracts.Prestec.NOM_CONTACTE, prestec.getNomContacte());
        values.put(DBContracts.Prestec.NOM_ITEM, prestec.getNomItem());
        values.put(DBContracts.Prestec.DATA, prestec.getData());
        db.insert(DBContracts.Prestec.TABLE_NAME, null, values);
        carregaDades();
    }

    public void del(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int x = db.delete(DBContracts.Prestec.TABLE_NAME, DBContracts.Prestec._ID + "=?",
                new String[]{ String.valueOf(id) });
        carregaDades();
    }

    public int size() {
        return dades.getCount();
    }

    public Cursor getDades() {
        return dades;
    }
}
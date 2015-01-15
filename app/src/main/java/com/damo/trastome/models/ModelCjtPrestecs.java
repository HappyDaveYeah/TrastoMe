package com.damo.trastome.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.damo.trastome.DBContracts;
import com.damo.trastome.DBHelper;
import com.damo.trastome.Prestec;

public class ModelCjtPrestecs {
    private static final String LOG_TAG = "ModelCjtPrestecs";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtPrestecs(Context context) {
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
        /*
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContracts.Prestec.ID_CONTACTE, prestec.getClass());
        values.put(DBContracts.Prestec.NOM_ITEM, item);
        values.put(DBContracts.Prestec.DATA, Utils.getDateTime());
        db.insert(DBContracts.Prestec.TABLE_NAME, null, values);
        carregaDades();
        */
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
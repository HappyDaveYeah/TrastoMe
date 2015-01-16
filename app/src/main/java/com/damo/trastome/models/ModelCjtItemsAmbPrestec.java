package com.damo.trastome.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.ContactsContract;
import android.util.Log;

import com.damo.trastome.dao.Item;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.db.DBHelper;

public class ModelCjtItemsAmbPrestec extends ModelCjtItems {
    private static final String LOG_TAG = "ModelCjtItemsAmbPrestec";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtItemsAmbPrestec(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades();
    }

    public void carregaDades() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = db.query(DBContracts.Item.TABLE_NAME, DBContracts.Item.COLUMNS, null,
                null, null, null, DBContracts.Item.NOM + " ASC", null);
    }

    public void add(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContracts.Item.NOM, item.getNom());
        db.insert(DBContracts.Item.TABLE_NAME, null, values);
        carregaDades();
    }

    @Override
    public Cursor getDades() {
        return dades;
    }

    @Override
    public int getPosition(String nomItem) {
        if (dades.moveToFirst()) {
            do {
                if (nomItem.equals(dades.getString(dades.getColumnIndex(DBContracts.Item.NOM)))) {
                    return dades.getPosition();
                }
            } while (dades.moveToNext());
        }
        return -1;
    }
}
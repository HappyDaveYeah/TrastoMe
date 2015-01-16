package com.damo.trastome.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.damo.trastome.dao.Item;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.db.DBHelper;

public class ModelCjtItemsSensePrestec extends ModelCjtItems{
    private static final String LOG_TAG = "ModelCjtItemsSensePrestec";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtItemsSensePrestec(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades();
    }

    public void carregaDades() {
        SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();

        _QB.setTables(DBContracts.Item.TABLE_NAME +
                " LEFT OUTER JOIN " + DBContracts.Prestec.TABLE_NAME+ " ON " +
                DBContracts.Item.NOM+ " = " + DBContracts.Prestec.NOM_ITEM);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = _QB.query(db, null, DBContracts.Prestec.FULL_ID+ " IS NULL",
                null, null, null, DBContracts.Item.NOM+ " ASC", null);
    }

    @Override
    public Cursor getDades() {
        return dades;
    }

    @Override
    public int getPosition(String nomItem) {
        if (dades.moveToFirst()) {
            do {
                Log.i(LOG_TAG, "getPosition() -> nomItem: " + nomItem);
                Log.i(LOG_TAG, "getPosition() -> dades: " + dades.getString(dades.getColumnIndex(DBContracts.Item.NOM)));
                Log.i(LOG_TAG, "getPosition() -> position: " + dades.getPosition());
                if (nomItem.equals(dades.getString(dades.getColumnIndex(DBContracts.Item.NOM)))) {
                    return dades.getPosition();
                }
            } while (dades.moveToNext());
        }
        return -1;
    }

    public int size() {
        return dades.getCount();
    }

    public void add(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContracts.Item.NOM, item.getNom());
        db.insert(DBContracts.Item.TABLE_NAME, null, values);
        carregaDades();
    }
}
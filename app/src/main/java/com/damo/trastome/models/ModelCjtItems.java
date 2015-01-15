package com.damo.trastome.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.damo.trastome.dao.Item;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.db.DBHelper;

public class ModelCjtItems {
    private static final String LOG_TAG = "ModelCjtItems";
    private DBHelper dbHelper;
    private Cursor dades;

    public ModelCjtItems(Context context) {
        dbHelper = DBHelper.getInstance(context);
        carregaDades();
    }

    public void carregaDades() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        dades = db.query(DBContracts.Item.TABLE_NAME, DBContracts.Item.COLUMNS, null,
                null, null, null, DBContracts.Item.NOM + " ASC", null);
    }

    public Cursor getItemsSensePrestec() {
        SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();

        _QB.setTables(DBContracts.Item.TABLE_NAME +
                " LEFT OUTER JOIN " + DBContracts.Prestec.TABLE_NAME+ " ON " +
                DBContracts.Item.NOM+ " = " + DBContracts.Prestec.NOM_ITEM);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return _QB.query(db, null, DBContracts.Prestec.FULL_ID+ " IS NULL",
                null, null, null, DBContracts.Item.NOM+ " ASC", null);
    }

    public int sizeItemsSensePrestec() {
        return getItemsSensePrestec().getCount();
    }

    public void add(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContracts.Item.NOM, item.getNom());
        db.insert(DBContracts.Item.TABLE_NAME, null, values);
        carregaDades();
    }
}
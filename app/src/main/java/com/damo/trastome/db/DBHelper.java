package com.damo.trastome.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.damo.trastome.Utils.Utils;

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBHelper";
    private static DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DBContracts.DB_NAME, null, DBContracts.SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContracts.Item.CREATE);
        db.execSQL(DBContracts.Prestec.CREATE);
        crearDadesInicials(db);
    }

    private void crearDadesInicials(SQLiteDatabase db) {
        insertItem(db, "DVD Matrix");
        insertItem(db, "DVD Matrix II");
        insertItem(db, "Lambrusco");
        insertItem(db, "Joc Hammerwatch");
        insertPrestec(db, 77, "Sonia", "DVD Matrix");
    }

    private long insertPrestec(SQLiteDatabase db, long idContacte, String nomContacte, String item) {
        ContentValues values = new ContentValues();
        values.put(DBContracts.Prestec.ID_CONTACTE, idContacte);
        values.put(DBContracts.Prestec.NOM_CONTACTE, nomContacte);
        values.put(DBContracts.Prestec.NOM_ITEM, item);
        values.put(DBContracts.Prestec.DATA, Utils.getDateTime());
        return db.insert(DBContracts.Prestec.TABLE_NAME, null, values);
    }

    private long insertItem(SQLiteDatabase db, String item) {
        ContentValues values = new ContentValues();
        values.put(DBContracts.Item.NOM, item);
        return db.insert(DBContracts.Item.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContracts.Item.DELETE);
        db.execSQL(DBContracts.Prestec.DELETE);
        onCreate(db);
    }

    // Singleton pattern
    public synchronized static DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }
}

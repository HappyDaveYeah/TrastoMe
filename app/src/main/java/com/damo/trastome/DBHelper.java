package com.damo.trastome;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HappyDave on 07/01/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBHelper";
    private static DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DBContracts.DB_NAME, null, DBContracts.SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContracts.Item.CREATE);
        db.execSQL(DBContracts.Contacte.CREATE);
        db.execSQL(DBContracts.Prestec.CREATE);
        Log.d(LOG_TAG, "onCreate");
        crearDadesInicials(db);
    }

    private void crearDadesInicials(SQLiteDatabase db) {
        long cntDavid = insertContacte(db, "David");
        insertContacte(db, "Antoni");
        insertItem(db, "DVD Matrix");
        insertItem(db, "DVD Matrix II");
        insertItem(db, "Lambrusco");
        insertItem(db, "Joc Hammerwatch");
        insertPrestec(db, cntDavid, "DVD Matrix");
    }

    private void insertPrestec(SQLiteDatabase db, long idContacte, String item) {
        ContentValues values = new ContentValues();
        values.put(DBContracts.Prestec.ID_CONTACTE, idContacte);
        values.put(DBContracts.Prestec.NOM_ITEM, item);
        values.put(DBContracts.Prestec.DATA, Utils.getDateTime());
        db.insert(DBContracts.Prestec.TABLE_NAME, null, values);
    }

    private long insertContacte(SQLiteDatabase db, String nom) {
        ContentValues values = new ContentValues();
        values.put(DBContracts.Contacte.NOM, nom);
        return db.insert(DBContracts.Contacte.TABLE_NAME, null, values);
    }

    private void insertItem(SQLiteDatabase db, String item) {
        ContentValues values = new ContentValues();
        values.put(DBContracts.Item.NOM, item);
        db.insert(DBContracts.Item.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContracts.Item.DELETE);
        db.execSQL(DBContracts.Contacte.DELETE);
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

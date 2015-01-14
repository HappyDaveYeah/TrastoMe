package com.damo.trastome;

import android.provider.BaseColumns;

/**
 * Created by HappyDave on 07/01/2015.
 */
public final class DBContracts {
    private DBContracts(){}

    public static final String DB_NAME = "TrastoMe.bd";
    public static final int SCHEMA = 1;
    private static final String COMA_SEP = ", ";

    public static abstract class Contacte implements BaseColumns {
        static final String TABLE_NAME = "Contacte";
        static final String NOM = "nom";

        static final String[] COLUMNS = {_ID, NOM};

        static final String CREATE = "CREATE TABLE " + TABLE_NAME
                + " ( "
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT"
                + COMA_SEP + NOM + " TEXT NOT NULL"
                + ");";

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Prestec implements BaseColumns {
        static final String TABLE_NAME = "Prestec";
        static final String ID_CONTACTE = "idContacte";
        static final String NOM_ITEM = "nomItem";
        static final String DATA = "data";

        static final String[] COLUMNS = {_ID, ID_CONTACTE, NOM_ITEM, DATA};

        static final String CREATE = "CREATE TABLE " + TABLE_NAME
                + " ( "
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT"
                + COMA_SEP + ID_CONTACTE + " INTEGER NOT NULL"
                + COMA_SEP + NOM_ITEM + " TEXT NOT NULL"
                + COMA_SEP + DATA + " TEXT NOT NULL"
                + COMA_SEP + "FOREIGN KEY ("+ ID_CONTACTE +") REFERENCES "+ Contacte.TABLE_NAME +" ("+ Contacte._ID +")"
                + COMA_SEP + "FOREIGN KEY ("+ NOM_ITEM +") REFERENCES "+ Item.TABLE_NAME +" ("+ Item.NOM +")"
                + ");";

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Item implements BaseColumns {
        static final String TABLE_NAME = "Item";
        static final String NOM = "nom";

        static final String[] COLUMNS = {NOM};

        static final String CREATE = "CREATE TABLE " + TABLE_NAME
                + " ( "
                + NOM + " TEXT PRIMARY KEY"
                + ");";

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}

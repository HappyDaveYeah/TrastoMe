package com.damo.trastome.dao;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.damo.trastome.db.DBContracts;

public class Prestec {
    private String nomItem;
    private long idContacte;
    private String data;

    public Prestec (String nomItem, long idContacte, String data) {
        this.nomItem = nomItem;
        this.idContacte = idContacte;
        this.data = data;
    }

    public Prestec(Cursor adaptadorItemCursor, Cursor adaptadorContactesCursor, String data) {
        this.nomItem = adaptadorItemCursor.getString(adaptadorItemCursor.getColumnIndex(DBContracts.Item.NOM));
        this.idContacte = adaptadorContactesCursor.getLong(adaptadorContactesCursor.getColumnIndex(ContactsContract.Data._ID));
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public String getNomItem(){
        return nomItem;
    }

    public long getIdContacte(){
        return idContacte;
    }
}

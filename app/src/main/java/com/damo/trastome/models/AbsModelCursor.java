package com.damo.trastome.models;

import android.database.Cursor;

/**
 * Created by HappyDave on 15/01/2015.
 */
public abstract class AbsModelCursor {
    protected Cursor dades;

    abstract void carregaDades();

    public int size() {
        return dades.getCount();
    }

    public Cursor getDades() {
        return dades;
    }

    abstract void add(Object object);

}

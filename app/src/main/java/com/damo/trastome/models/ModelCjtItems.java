package com.damo.trastome.models;

import android.database.Cursor;

/**
 * Created by HappyDave on 16/01/2015.
 */
public abstract class ModelCjtItems {
    public abstract Cursor getDades();

    public abstract int getPosition(String nomItem);
}

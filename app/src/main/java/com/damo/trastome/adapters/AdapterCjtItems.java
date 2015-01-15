package com.damo.trastome.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.damo.trastome.db.DBContracts;
import com.damo.trastome.models.ModelCjtItems;

public class AdapterCjtItems extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private ModelCjtItems modelCjtItems;


    public AdapterCjtItems(Context context, ModelCjtItems modelCjtItems, int flags) {
        super(context, modelCjtItems.getDades(), flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.modelCjtItems = modelCjtItems;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view;
        text.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Item.NOM)));
    }
}

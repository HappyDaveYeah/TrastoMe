package com.damo.trastome.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.damo.trastome.models.ModelCjtContactes;

public class AdapterCjtContactes extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private ModelCjtContactes modelCjtContactes;


    public AdapterCjtContactes(Context context, ModelCjtContactes modelCjtContactes, int flags) {
        super(context, modelCjtContactes.getDades(), flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.modelCjtContactes = modelCjtContactes;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view;
        text.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
    }
}

package com.damo.trastome.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.damo.trastome.models.ModelCjtContactes;

public class AdapterCjtContactes extends ResourceCursorAdapter{
    private LayoutInflater cursorInflater;
    private ModelCjtContactes modelCjtContactes;
    private int layoutId;

    public AdapterCjtContactes(Context context, int layout, ModelCjtContactes modelCjtContactes, int flags) {
        super(context, layout, modelCjtContactes.getDades(), flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layout;
        this.modelCjtContactes = modelCjtContactes;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(layoutId, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view;
        text.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
    }
}

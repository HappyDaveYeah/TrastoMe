package com.damo.trastome.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.damo.trastome.db.DBContracts;
import com.damo.trastome.models.ModelCjtItems;

public class AdapterCjtItems extends ResourceCursorAdapter {
    private LayoutInflater cursorInflater;
    private ModelCjtItems modelCjtItems;
    private int layoutId;

    public AdapterCjtItems(Context context, int layout, ModelCjtItems modelCjtItems, int flags) {
        super(context, layout, modelCjtItems.getItemsSensePrestec(), flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layout;
        this.modelCjtItems = modelCjtItems;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(layoutId, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view;
        text.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Item.NOM)));
    }

    @Override
    public long getItemId(int position) {
        Cursor cursor = getCursor();
        if (cursor != null) {
            if (cursor.moveToPosition(position)) {
                // first column of the left join
                return cursor.getLong(0);
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }
}

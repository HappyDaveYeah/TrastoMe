package com.damo.trastome;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdapterCjtPrestecs extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private ModelCjtPrestecs modelCjtPrestecs;


    public AdapterCjtPrestecs(Context context, ModelCjtPrestecs modelCjtPrestecs, int flags) {
        super(context, modelCjtPrestecs.getDades(), flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.modelCjtPrestecs = modelCjtPrestecs;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Prestec.NOM_ITEM)));
    }

    private void updateCursor() {
        changeCursor(modelCjtPrestecs.getDades());
    }

    public void add(Prestec p) {
        modelCjtPrestecs.add(p);
        updateCursor();
    }

    public void del(long id) {
        modelCjtPrestecs.del(id);
        updateCursor();
    }
}

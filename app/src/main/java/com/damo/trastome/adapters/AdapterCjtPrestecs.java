package com.damo.trastome.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.damo.trastome.R;
import com.damo.trastome.dao.Prestec;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.models.ModelCjtPrestecs;

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
        return cursorInflater.inflate(R.layout.row_prestec, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item = (TextView) view.findViewById(R.id.item_row);
        TextView data = (TextView) view.findViewById(R.id.data_row);
        TextView contacte = (TextView) view.findViewById(R.id.contacte_row);
        item.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Prestec.NOM_ITEM)));
        data.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Prestec.DATA)));
        contacte.setText(cursor.getString(cursor.getColumnIndex(DBContracts.Prestec.NOM_CONTACTE)));
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

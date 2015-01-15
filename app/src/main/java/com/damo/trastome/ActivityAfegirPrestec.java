package com.damo.trastome;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class ActivityAfegirPrestec extends Activity {

    private Spinner spinnerContactes;
    private Spinner spinnerItems;
    private SimpleCursorAdapter adaptadorContactes;
    private SimpleCursorAdapter adaptadorItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_prestec);
        inicialitza();
    }

    private void inicialitza() {
        findViews();
        setAdapter();
    }

    private void setAdapter() {
        ModelCjtContactes modelCjtContactes = new ModelCjtContactes(getApplicationContext());

        String[] columns = new String[]{Data.DISPLAY_NAME};

        adaptadorContactes =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        modelCjtContactes.getDades(),
                        columns,
                        new int[] {android.R.id.text1},
                        1);

        spinnerContactes.setAdapter(adaptadorContactes);

        DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorItems = db.query(DBContracts.Item.TABLE_NAME, DBContracts.Item.COLUMNS, null,
                null, null, null, DBContracts.Item.NOM + " ASC", null);

        columns = new String[]{DBContracts.Item.NOM};

        adaptadorItem =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        cursorItems,
                        columns,
                        new int[] {android.R.id.text1},
                        1);

        spinnerItems.setAdapter(adaptadorItem);
    }

    private void findViews() {
        spinnerContactes = (Spinner) findViewById(R.id.spnnr_moros);
        spinnerItems = (Spinner) findViewById(R.id.spnnr_item);
    }
}

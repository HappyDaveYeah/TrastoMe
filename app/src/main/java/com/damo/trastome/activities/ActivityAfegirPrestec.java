package com.damo.trastome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import com.damo.trastome.R;
import com.damo.trastome.adapters.AdapterCjtContactes;
import com.damo.trastome.adapters.AdapterCjtItems;
import com.damo.trastome.models.ModelCjtContactes;
import com.damo.trastome.models.ModelCjtItems;

public class ActivityAfegirPrestec extends Activity {

    private Spinner spinnerContactes;
    private Spinner spinnerItems;
    private AdapterCjtContactes adaptadorContactes;
    private AdapterCjtItems adaptadorItem;

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

        adaptadorContactes =
                new AdapterCjtContactes(this, modelCjtContactes, 0);

        spinnerContactes.setAdapter(adaptadorContactes);

        ModelCjtItems modelCjtItems = new ModelCjtItems(getApplicationContext());

        adaptadorItem =
                new AdapterCjtItems(this, modelCjtItems, 0);

        spinnerItems.setAdapter(adaptadorItem);
    }

    private void findViews() {
        spinnerContactes = (Spinner) findViewById(R.id.spnnr_moros);
        spinnerItems = (Spinner) findViewById(R.id.spnnr_item);
    }
}

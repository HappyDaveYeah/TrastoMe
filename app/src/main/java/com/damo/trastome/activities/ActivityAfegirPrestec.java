package com.damo.trastome.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.damo.trastome.R;
import com.damo.trastome.Utils;
import com.damo.trastome.adapters.AdapterCjtContactes;
import com.damo.trastome.adapters.AdapterCjtItems;
import com.damo.trastome.dao.Prestec;
import com.damo.trastome.db.DBContracts;
import com.damo.trastome.models.ModelCjtContactes;
import com.damo.trastome.models.ModelCjtItems;
import com.damo.trastome.models.ModelCjtPrestecs;

public class ActivityAfegirPrestec extends Activity {

    private Spinner spinnerContactes;
    private Spinner spinnerItems;
    private AdapterCjtContactes adaptadorContactes;
    private AdapterCjtItems adaptadorItem;
    private Button btnCancelar;
    private Button btnAcceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_prestec);
        inicialitza();
    }

    private void inicialitza() {
        findViews();
        setAdapter();
        setListeners();
    }

    private void findViews() {
        spinnerContactes = (Spinner) findViewById(R.id.spnnr_moros);
        spinnerItems = (Spinner) findViewById(R.id.spnnr_item);
        btnAcceptar = (Button) findViewById(R.id.btn_prestec_acceptar);
        btnCancelar = (Button) findViewById(R.id.btn_prestec_cancelar);
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

    private void setListeners() {
        btnAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAcceptar();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancelar();
            }
        });
    }

    private void onClickAcceptar() {
        afegirPrestec();
        returnMyActivity(RESULT_OK);
    }

    private void afegirPrestec() {
        ModelCjtPrestecs modelCjtPrestecs = new ModelCjtPrestecs(getApplicationContext());
        modelCjtPrestecs.add(new Prestec(adaptadorItem.getCursor(), adaptadorContactes.getCursor(), Utils.getDateTime()));
    }

    private void onClickCancelar() {
        returnMyActivity(RESULT_CANCELED);
    }

    private void returnMyActivity(int flag) {
        Intent intent = new Intent();
        setResult(flag, intent);
        finish();
    }
}


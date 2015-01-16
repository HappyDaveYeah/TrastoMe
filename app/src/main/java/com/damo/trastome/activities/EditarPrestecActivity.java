package com.damo.trastome.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.damo.trastome.R;
import com.damo.trastome.Utils.Consts;
import com.damo.trastome.adapters.AdapterCjtContactes;
import com.damo.trastome.adapters.AdapterCjtItems;
import com.damo.trastome.dao.Prestec;
import com.damo.trastome.models.ModelCjtItemsSensePrestec;
import com.damo.trastome.models.ModelCjtPrestecs;
import com.damo.trastome.models.ModelCursorCjtContactes;

public class EditarPrestecActivity extends Activity {

    public static final String LOG_TAG = "EditarPrestecActivity";
    private Spinner spinnerContactes;
    private Spinner spinnerItems;
    private AdapterCjtContactes adaptadorContactes;
    private AdapterCjtItems adaptadorItem;
    private Button btnCancelar;
    private Button btnAcceptar;
    private long idPrestec;
    private Prestec prestec;
    private ModelCjtPrestecs modelCjtPrestecs;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_prestec);
        getSharedPref();
        inicialitza();
        Log.i(LOG_TAG, "onCreate");
    }

    private void inicialitza() {
        findViews();
        setAdapter();
        setListeners();
    }

    private void getSharedPref() {
        pref = getSharedPreferences(Consts.PREFS_NAME, 0);
        idPrestec = pref.getLong(Consts.ID_PRESTEC, -1);
        Log.i(LOG_TAG, String.valueOf(idPrestec));
    }

    private void findViews() {
        spinnerContactes = (Spinner) findViewById(R.id.spnnr_moros);
        spinnerItems = (Spinner) findViewById(R.id.spnnr_item);
        btnAcceptar = (Button) findViewById(R.id.btn_prestec_modificar);
        btnCancelar = (Button) findViewById(R.id.btn_prestec_cancelar);
    }

    private void setAdapter() {
        modelCjtPrestecs = new ModelCjtPrestecs(this);
        prestec = modelCjtPrestecs.get(idPrestec);
        Log.i(LOG_TAG, "setAdapter -> idPrestec: " + idPrestec);
        modelCjtPrestecs.del(idPrestec);

        ModelCursorCjtContactes modelCjtContactes = new ModelCursorCjtContactes(getApplicationContext());

        adaptadorContactes =
                new AdapterCjtContactes(this, android.R.layout.simple_spinner_item, modelCjtContactes, 0);

        adaptadorContactes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContactes.setAdapter(adaptadorContactes);

        ModelCjtItemsSensePrestec modelCjtItemsSensePrestec = new ModelCjtItemsSensePrestec(getApplicationContext());

        adaptadorItem =
                new AdapterCjtItems(this, android.R.layout.simple_spinner_item, modelCjtItemsSensePrestec, 0);

        adaptadorItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItems.setAdapter(adaptadorItem);

        long idContacte = prestec.getIdContacte();
        Log.i(LOG_TAG, "setAdapter -> idContacte: " + idContacte);
        int position = adaptadorContactes.getPosition(idContacte);

        spinnerContactes.setSelection(position);
        spinnerItems.setSelection(adaptadorItem.getPosition(prestec.getNomItem()));
    }

    private void setListeners() {
        btnAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickModificar();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancelar();
            }
        });
    }

    private void onClickModificar() {
        modificarPrestec();
        returnMyActivity(RESULT_OK);
    }

    private void modificarPrestec() {
        modelCjtPrestecs.add(
                new Prestec((Cursor) spinnerItems.getSelectedItem(),
                        (Cursor) spinnerContactes.getSelectedItem(),
                        prestec.getData())
        );
    }

    private void onClickCancelar() {
        modelCjtPrestecs.add(prestec);
        returnMyActivity(RESULT_CANCELED);
    }

    private void returnMyActivity(int flag) {
        Intent intent = new Intent();
        setResult(flag, intent);
        finish();
    }
}

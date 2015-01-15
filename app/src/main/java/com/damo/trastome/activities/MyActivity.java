package com.damo.trastome.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.damo.trastome.R;
import com.damo.trastome.adapters.AdapterCjtPrestecs;
import com.damo.trastome.dao.Item;
import com.damo.trastome.models.ModelCjtItems;
import com.damo.trastome.models.ModelCjtPrestecs;


public class MyActivity extends Activity {

    private static final String LOG_TAG = "MyActivity";
    public static final int AFEGIR_REQUEST = 1;
    ListView prestecList;
    AdapterCjtPrestecs adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        inicialitza();
    }

    private void inicialitza() {
        ModelCjtPrestecs modelReal = new ModelCjtPrestecs(this);

        prestecList = (ListView) findViewById(R.id.prectec_list);
        adaptador = new AdapterCjtPrestecs(this, modelReal, 0);
        prestecList.setAdapter(adaptador);

        prestecList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Esborra l'objecte polsat
                adaptador.del(id);
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prestec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nou_prestec:
                afegirPrestec();
                return true;
            case R.id.nou_item:
                onClickAfegirItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void afegirPrestec() {
        if (new ModelCjtItems(getApplicationContext()).sizeItemsSensePrestec() > 0)
            goToAfegirPrestecActivity();
        else
            Toast.makeText(
                    getApplicationContext(),
                    getApplicationContext().getString(R.string.no_items_toast),
                    Toast.LENGTH_SHORT
            ).show();
    }

    private void onClickAfegirItem() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        final EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_titol_afegir_item))
                .setMessage(getString(R.string.dialog_msg_afegir_item))
                .setView(input)
                .setPositiveButton(getString(R.string.acceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        afegirItem(input.getText());
                    }
                }).setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();
    }

    private void afegirItem(Editable text) {
        ModelCjtItems modelCjtItems = new ModelCjtItems(getApplicationContext());
        modelCjtItems.add(new Item(text.toString()));
    }

    private void goToAfegirPrestecActivity() {
        Intent intent = new Intent(getApplicationContext(), AfegirPrestecActivity.class);
        startActivityForResult(intent, AFEGIR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AFEGIR_REQUEST) {
            if (resultCode == RESULT_OK) {
                adaptador.changeCursor(new ModelCjtPrestecs(this).getDades());
            }
        }
    }
}


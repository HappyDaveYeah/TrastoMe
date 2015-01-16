package com.damo.trastome.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.damo.trastome.R;
import com.damo.trastome.Utils.Consts;
import com.damo.trastome.adapters.AdapterCjtPrestecs;
import com.damo.trastome.dao.Item;
import com.damo.trastome.models.ModelCjtItemsSensePrestec;
import com.damo.trastome.models.ModelCjtPrestecs;


public class MyActivity extends Activity {

    private static final String LOG_TAG = "MyActivity";
    private static final int AFEGIR_REQUEST = 1;
    private static final int EDITAR_REQUEST = 2;
    private ListView prestecList;
    private AdapterCjtPrestecs adaptador;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        inicialitza();
    }

    private void inicialitza() {
        pref = getSharedPreferences(Consts.PREFS_NAME, 0);

        ModelCjtPrestecs modelReal = new ModelCjtPrestecs(this);

        prestecList = (ListView) findViewById(R.id.prectec_list);
        adaptador = new AdapterCjtPrestecs(this, modelReal, 0);
        prestecList.setAdapter(adaptador);

        registerForContextMenu(prestecList);
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
        if (new ModelCjtItemsSensePrestec(getApplicationContext()).size() > 0)
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
        ModelCjtItemsSensePrestec modelCjtItems = new ModelCjtItemsSensePrestec(getApplicationContext());
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
                actualitzarModel();
            }
        }
        else if (requestCode == EDITAR_REQUEST) {
            actualitzarModel();
        }
    }

    private void actualitzarModel() {
        adaptador.changeCursor(new ModelCjtPrestecs(this).getDades());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_item:
                clickEditContextual(info.id);
                return true;
            case R.id.esborrar_item:
                clickEsborrarContextual(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void clickEditContextual(long id) {
        Intent intent = new Intent(getApplicationContext(), EditarPrestecActivity.class);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(Consts.ID_PRESTEC, id);
        Log.i(LOG_TAG, String.valueOf(id));
        editor.apply();
        startActivityForResult(intent, EDITAR_REQUEST);
    }

    private void clickEsborrarContextual(final long id) {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.esborrar_msg_dialog))
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(getString(R.string.esborrar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        adaptador.del(id);
                        Toast.makeText(
                                getApplicationContext(),
                                getApplicationContext().getString(R.string.trasto_esborrat_toast),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }).setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();
    }
}


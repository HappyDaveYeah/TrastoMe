package com.damo.trastome.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.damo.trastome.R;
import com.damo.trastome.adapters.AdapterCjtPrestecs;
import com.damo.trastome.models.ModelCjtPrestecs;


public class MyActivity extends Activity {

    private static final String LOG_TAG = "MyActivity";
    ListView prestecList;
    AdapterCjtPrestecs adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        inicialitza();
    }

    private void inicialitza() {
        final ModelCjtPrestecs modelReal = new ModelCjtPrestecs(this);

        prestecList = (ListView) findViewById(R.id.prectec_list);
        adaptador = new AdapterCjtPrestecs(this, modelReal, 0);
        prestecList.setAdapter(adaptador);

        /*
        boto = (Button) findViewById(R.id.button);
        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClic();
            }
        });
        */

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
            case R.id.buscar_prestec:
                buscarPrestec();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void afegirPrestec() {
        Intent intent = new Intent(getApplicationContext(), ActivityAfegirPrestec.class);
        //finish();
        startActivity(intent);
    }

    private void buscarPrestec() {

    }

}


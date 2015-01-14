package com.damo.trastome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class MyActivity extends Activity {

    private static final String LOG_TAG = "MyActivity";
    ListView prestecList;
    AdapterMeu adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        inicialitza();
    }

    private void inicialitza() {
        final ModelCjtPrestecs modelReal = new ModelCjtPrestecs(this);

        prestecList = (ListView) findViewById(R.id.prectec_list);
        adaptador = new AdapterMeu(this, modelReal, 0);
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
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

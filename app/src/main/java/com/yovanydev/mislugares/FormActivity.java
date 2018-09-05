package com.yovanydev.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.yovanydev.mislugares.utilities.ToolbarUtility;
import com.yovanydev.mislugares.view.ViewPlaceActivity;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ToolbarUtility.showToolbar(this,getResources().getString(R.string.app_name),false);
    }

    /*----------------------------------------------------------------------------------------------
    Lanzar el Activity ViewPlace
     */
    public void launchViewPlace(View view, long id) {
        Intent intent = new Intent(this, ViewPlaceActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /*----------------------------------------------------------------------------------------------
    Lanzar un Dialogo para Buscar un Lugar
     */
    public void launchSimpleDialog() {
        final EditText editTextIdPlace = new EditText(this);
        editTextIdPlace.setText("0");

        new AlertDialog.Builder(this)
                .setTitle("Selección de Lugar")
                .setMessage("Indica su ID:")
                .setView(editTextIdPlace)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long id = Long.parseLong(editTextIdPlace.getText().toString());
                        launchViewPlace(null, id);
                    }
                })
                .setNegativeButton("CANCELAR",null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_search:
                launchSimpleDialog();
                break;
        }

        return true;
    }
}

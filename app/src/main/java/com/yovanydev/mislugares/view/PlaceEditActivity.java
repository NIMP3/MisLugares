package com.yovanydev.mislugares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.yovanydev.mislugares.R;
import com.yovanydev.mislugares.model.Place;
import com.yovanydev.mislugares.model.Places;
import com.yovanydev.mislugares.model.TypePlace;
import com.yovanydev.mislugares.utilities.ToolbarUtility;

public class PlaceEditActivity extends AppCompatActivity {

    private EditText etNamePlace;
    private EditText etAddressPlace;
    private EditText etPhonePlace;
    private EditText etUrlPlace;
    private EditText etCommentaryPlace;

    private Spinner spTypePlace;

    private long id;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_edit);

        //Obtenemos el lugar que se quiere editar
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id",-1);
        place = Places.searchPlace((int) id);

        ToolbarUtility.showToolbar(
                this,
                getResources().getString(R.string.title_edit_place),
                false);

        etNamePlace = findViewById(R.id.etPlaceName);
        etAddressPlace = findViewById(R.id.etDir);
        etPhonePlace = findViewById(R.id.etPhone);
        etUrlPlace = findViewById(R.id.etUrl);
        etCommentaryPlace = findViewById(R.id.etComent);

        spTypePlace = findViewById(R.id.spinnerTypePlace);

        //Seteamos los datos en los respectivos views del formulario
        etNamePlace.setText(place.getNamePlace());
        etAddressPlace.setText(place.getAddressPlace());
        etPhonePlace.setText((String.valueOf(place.getPhonePlace())));
        etUrlPlace.setText(place.getUrlPlace());
        etCommentaryPlace.setText(place.getCommentaryPlace());

        //Enviamos los respectivos valores al Spinner = TipoLugar
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, TypePlace.getNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTypePlace.setAdapter(adapter);
        spTypePlace.setSelection(place.getTypePlace().ordinal());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_place,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_save:
                //Guardamos el Lugar que se ha editado
                place.setNamePlace(etNamePlace.getText().toString());
                place.setAddressPlace(etAddressPlace.getText().toString());
                place.setPhonePlace(Integer.parseInt(etPhonePlace.getText().toString()));
                place.setUrlPlace(etUrlPlace.getText().toString());
                place.setCommentaryPlace(etCommentaryPlace.getText().toString());
                place.setTypePlace(TypePlace.values()[spTypePlace.getSelectedItemPosition()]);
                finish();
            case R.id.item_cancel:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

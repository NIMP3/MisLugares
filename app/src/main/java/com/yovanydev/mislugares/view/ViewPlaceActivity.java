package com.yovanydev.mislugares.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yovanydev.mislugares.R;
import com.yovanydev.mislugares.model.Place;
import com.yovanydev.mislugares.model.Places;

import java.text.DateFormat;
import java.util.Date;

public class ViewPlaceActivity extends AppCompatActivity {

    TextView textViewNamePlace;
    TextView textViewAddressPlace;
    TextView textViewPhonePlace;
    TextView textViewUrlPlace;
    TextView textViewComentaryPlace;
    TextView textViewDatePlace;
    TextView textViewTimePlace;

    RatingBar ratingBarScorePlace;
    ImageView imageViewPicturePlace;

    private long id;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id",-1);
        place = Places.searchPlace((int) id);

        showToolbar(place.getTypePlace().getText(),false);

        textViewNamePlace = findViewById(R.id.textViewNamePlace);
        textViewAddressPlace = findViewById(R.id.textViewAddressPlace);
        textViewPhonePlace = findViewById(R.id.textViewPhonePlace);
        textViewUrlPlace = findViewById(R.id.textViewUrlPlace);
        textViewComentaryPlace = findViewById(R.id.textViewComentPlace);
        textViewDatePlace = findViewById(R.id.textViewDatePlace);
        textViewTimePlace = findViewById(R.id.textViewTimePlace);

        ratingBarScorePlace = findViewById(R.id.ratingBarScorePlace);
        imageViewPicturePlace = findViewById(R.id.imageViewPicturePlace);

        updateDateView();
    }

    /*----------------------------------------------------------------------------------------------
    Crear un Dialogo simple
     */
    public void launchSimpleDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Borrado de Lugar")
                .setMessage("¿Estas seguro que quieres eliminar este lugar?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Places.deletePlace((int) id);
                    }
                })
                .setNegativeButton("CANCELAR", null)
                .show();

    }

    /*----------------------------------------------------------------------------------------------
    Mostrar el toolbar con diferentes caracteristicas
     */
    public void showToolbar(String title, Boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        getSupportActionBar().setIcon(place.getTypePlace().getResource());
    }

    /*----------------------------------------------------------------------------------------------
    Actualizar los datos de la Vista
     */
    public void updateDateView() {
        //getSupportActionBar().setTitle(place.getTypePlace().getText());

        textViewNamePlace.setText(place.getNamePlace());
        textViewAddressPlace.setText(place.getAddressPlace());
        textViewPhonePlace.setText(String.valueOf(place.getPhonePlace()));
        textViewUrlPlace.setText(place.getUrlPlace());
        textViewComentaryPlace.setText(place.getCommentaryPlace());

        textViewDatePlace.setText(DateFormat.getDateInstance().format(new Date(place.getDatePlace())));
        textViewTimePlace.setText(DateFormat.getTimeInstance().format(new Date(place.getDatePlace())));

        ratingBarScorePlace.setRating(place.getScorePlace());
        Picasso.get().load(place.getPhotoPlace()).into(imageViewPicturePlace);

        ratingBarScorePlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                place.setScorePlace(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                return true;
            case R.id.item_arrive:
                return true;
            case R.id.item_edit:
                Intent intent = new Intent(this, PlaceEditActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,1234);
                return true;
            case R.id.item_delete:
                launchSimpleDialog();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) updateDateView();
    }
}

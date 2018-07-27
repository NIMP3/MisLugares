package com.yovanydev.mislugares.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private long id;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id",-1);
        place = Places.searchPlace((int) id);

        TextView textViewNamePlace = findViewById(R.id.textViewNamePlace);
        TextView textViewTypePlace = findViewById(R.id.textViewTypePlace);
        TextView textViewAddressPlace = findViewById(R.id.textViewAddressPlace);
        TextView textViewPhonePlace = findViewById(R.id.textViewPhonePlace);
        TextView textViewUrlPlace = findViewById(R.id.textViewUrlPlace);
        TextView textViewComentaryPlace = findViewById(R.id.textViewComentPlace);
        TextView textViewDatePlace = findViewById(R.id.textViewDatePlace);
        TextView textViewTimePlace = findViewById(R.id.textViewTimePlace);

        RatingBar ratingBarScorePlace = findViewById(R.id.ratingBarScorePlace);
        ImageView imageViewPicturePlace = findViewById(R.id.imageViewPicturePlace);

        textViewNamePlace.setText(place.getNamePlace());
        textViewTypePlace.setText(place.getTypePlace().getText());
        textViewAddressPlace.setText(place.getAddressPlace());
        textViewPhonePlace.setText(place.getPhonePlace()+"");
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
                return true;
            case R.id.item_delete:
                launchSimpleDialog();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

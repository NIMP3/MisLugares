package com.yovanydev.mislugares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}

package com.yovanydev.mislugares.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yovanydev.mislugares.FormActivity;
import com.yovanydev.mislugares.R;
import com.yovanydev.mislugares.model.Place;
import com.yovanydev.mislugares.view.ViewPlaceActivity;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    private List<Place> places;
    private Activity activity;
    private int resource;

    public PlaceAdapter(List<Place> places, Activity activity, int resource) {
        this.places = places;
        this.activity = activity;
        this.resource = resource;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, final int position) {
        Place place = places.get(position);

        Picasso.get()
                .load(place.getPhotoPlace())
                .error(R.drawable.place)
                .into(holder.ivPlacePicture);

        holder.rbPlaceScore.setRating(place.getScorePlace());
        holder.tvPlaceName.setText(place.getNamePlace());
        holder.tvPlaceAddress.setText(place.getAddressPlace());
        holder.cvPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewPlaceActivity.class);
                intent.putExtra("id",(long) position);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        private CardView cvPlace;
        private ImageView ivPlacePicture;
        private RatingBar rbPlaceScore;
        private TextView tvPlaceName;
        private TextView tvPlaceAddress;

        PlaceViewHolder(View itemView) {
            super(itemView);

            cvPlace = itemView.findViewById(R.id.cvPlace);
            ivPlacePicture = itemView.findViewById(R.id.ivPlacePicture);
            rbPlaceScore = itemView.findViewById(R.id.rbPlaceScore);
            tvPlaceName = itemView.findViewById(R.id.tvPlaceName);
            tvPlaceAddress = itemView.findViewById(R.id.tvPlaceAddress);
        }
    }
}

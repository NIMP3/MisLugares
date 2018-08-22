package com.yovanydev.mislugares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.yovanydev.mislugares.R;
import com.yovanydev.mislugares.adapter.PlaceAdapter;
import com.yovanydev.mislugares.model.Places;

public class PlaceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        RecyclerView recyclerView = findViewById(R.id.rvPlace);

        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        PlaceAdapter adapter = new PlaceAdapter(Places.getPlaces(), this, R.layout.cardview_place);
        recyclerView.setAdapter(adapter);
    }
}

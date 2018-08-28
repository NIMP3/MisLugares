package com.yovanydev.mislugares.utilities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yovanydev.mislugares.R;

public class ToolbarUtility {
    /*----------------------------------------------------------------------------------------------
    * Muestra el Toolbar con algunas opciones */
    public static void showToolbar(Activity activity, String title, Boolean upButton) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        ((AppCompatActivity) activity).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}

package com.yovanydev.mislugares.model;

import android.graphics.drawable.Drawable;

import com.yovanydev.mislugares.R;

public enum TypePlace {
    OTROS ("Otros", R.drawable.ic_arrow),
    RESTAURANTE ("Restaurante", R.drawable.ic_arrow),
    BAR ("Bar", R.drawable.ic_arrow),
    COPAS ("Copas", R.drawable.ic_arrow),
    ESPECTACULO ("Espectaculo", R.drawable.ic_arrow),
    HOTEL ("Hotel", R.drawable.ic_arrow),
    COMPRAS ("Compras", R.drawable.ic_arrow),
    EDUCACION ("Educación", R.drawable.ic_arrow),
    DEPORTE ("Deporte", R.drawable.ic_arrow),
    NATURALEZA ("Naturaleza", R.drawable.ic_arrow),
    HISTORICO ("Historico", R.drawable.ic_arrow),
    GASOLINERA ("Gasolinera", R.drawable.ic_arrow);

    private final String text;
    private final int resource;

    TypePlace(String text, int resource) {
        this.text = text;
        this.resource = resource;
    }

    public String getText() {
        return text;
    }

    public int getResource() {
        return resource;
    }
}

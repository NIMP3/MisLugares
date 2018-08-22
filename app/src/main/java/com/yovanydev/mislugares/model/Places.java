package com.yovanydev.mislugares.model;

import com.yovanydev.mislugares.model.TypePlace;

import java.util.ArrayList;
import java.util.List;

public class Places {

    private static List<Place> places = examplePlaces();

    public Places() {
        places = examplePlaces();
    }

    public static Place searchPlace(int id) {
        return places.get(id);
    }

    public static void addPlace(Place place) {
        places.add(place);
    }

    public static int newPlace() {
        Place place = new Place();
        places.add(place);
        return places.size()-1;
    }

    public static void deletePlace(int id) {
        places.remove(id);
    }


    public static int sizePlaces() {
        return places.size();
    }

    private static ArrayList<Place> examplePlaces() {
        ArrayList<Place> places = new ArrayList<>();

        places.add(new Place(
                "Escuela Politécnica Superior de Gandía",
                "C/ Paranimf, 1 46730 Gandia (SPAIN)",
                -0.166093, 38.995656, TypePlace.EDUCACION,
                962849300, "http://www.espg.upv.es",
                "Uno de los mejores places para formarse",
                3,"http://www.novalandtours.com/images/guide/guilin.jpg"
        ));

        places.add(new Place(
                "Al de siempre",
                "P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)",
                -0.190642, 38.925857, TypePlace.BAR,
                636472405, "",
                "No te pierdas el arroz en calabaza.",
                3,"https://miviaje.com/wp-content/uploads/2016/05/shutterstock_250683580.jpg"
        ));

        places.add(new Place(
                "androidcurso.com",
                "Ciberespacio",
                0.0, 0.0, TypePlace.EDUCACION,
                962849300, "http://androidcurso.com",
                "Amplia tus conocimientos sobre Android.",
                5,"https://i.imgur.com/PQZ7otL.jpg"
        ));

        places.add(new Place(
                "Barranco del Infierno",
                "Vía Verde del río Serpis. Villalonga (Valencia)",
                -0.295058, 38.867180, TypePlace.NATURALEZA,
                0, "http://sosegaos.blogpost.com.es/2009/02/lorcha-villalonga-via-verde-del-rio.html",
                "Espectacular ruta para bici o andar",
                4,"https://i.imgur.com/5d2HHEE.jpg"
        ));

        places.add(new Place(
                "La Vital",
                "Avda. de La Vital, 046701 Gandia (Valencia)",
                -0.1720092, 38.9705949, TypePlace.COMPRAS,
                962881070, "http://www.lavital.es/",
                "El típico centro comercial",
                2,"https://i.imgur.com/5d2HHEE.jpg"
        ));

        return places;
    }

    public static List<Place> getPlaces() {
        return places;
    }
}

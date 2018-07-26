package com.yovanydev.mislugares.model;

public class GeoPoint {
    //Atributos
    private double longitude;
    private double latitude;

    /*----------------------------------------------------------------------------------------------
    *Constructor
     */
    public GeoPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    /*----------------------------------------------------------------------------------------------
    *Obtiene la distancia de un GeoPoint
     */
    public double getDistance(GeoPoint point) {
        final double EARTH_RADIO = 6371000; // en metros

        double dLat = Math.toRadians(latitude - point.latitude);
        double dLon = Math.toRadians(longitude - point.longitude);
        double lat1 = Math.toRadians(point.latitude);
        double lat2 = Math.toRadians(latitude);

        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2)+
                Math.sin(dLon/2) * Math.sin(dLon/2)*
                Math.cos(lat1) * Math.cos(lat2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * EARTH_RADIO;
    }

    /*----------------------------------------------------------------------------------------------
    *Getters y Setters de la clase GeoPoint
     */

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

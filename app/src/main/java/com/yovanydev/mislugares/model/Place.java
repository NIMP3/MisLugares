package com.yovanydev.mislugares.model;

public class Place {

    private String namePlace;
    private String addressPlace;
    private GeoPoint positionPlace;
    private String photoPlace;
    private int phonePlace;
    private String urlPlace;
    private String commentaryPlace;
    private long datePlace;
    private float scorePlace;
    private TypePlace typePlace;

    public Place(String namePlace, String addressPlace, double longitude,
                 double latitude, TypePlace typePlace, int phonePlace,
                 String urlPlace, String commentaryPlace, float scorePlace, String photoPlace) {

        this.positionPlace = new GeoPoint(longitude, latitude);
        this.datePlace = System.currentTimeMillis();

        this.namePlace = namePlace;
        this.addressPlace = addressPlace;
        this.phonePlace = phonePlace;
        this.urlPlace = urlPlace;
        this.commentaryPlace = commentaryPlace;
        this.scorePlace = scorePlace;
        this.typePlace = typePlace;
        this.photoPlace = photoPlace;
    }

    public Place() {

    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getAddressPlace() {
        return addressPlace;
    }

    public void setAddressPlace(String addressPlace) {
        this.addressPlace = addressPlace;
    }

    public GeoPoint getPositionPlace() {
        return positionPlace;
    }

    public void setPositionPlace(GeoPoint positionPlace) {
        this.positionPlace = positionPlace;
    }

    public String getPhotoPlace() {
        return photoPlace;
    }

    public void setPhotoPlace(String photoPlace) {
        this.photoPlace = photoPlace;
    }

    public int getPhonePlace() {
        return phonePlace;
    }

    public void setPhonePlace(int phonePlace) {
        this.phonePlace = phonePlace;
    }

    public String getUrlPlace() {
        return urlPlace;
    }

    public void setUrlPlace(String urlPlace) {
        this.urlPlace = urlPlace;
    }

    public String getCommentaryPlace() {
        return commentaryPlace;
    }

    public void setCommentaryPlace(String commentaryPlace) {
        this.commentaryPlace = commentaryPlace;
    }

    public long getDatePlace() {
        return datePlace;
    }

    public void setDatePlace(long datePlace) {
        this.datePlace = datePlace;
    }

    public float getScorePlace() {
        return scorePlace;
    }

    public void setScorePlace(float scorePlace) {
        this.scorePlace = scorePlace;
    }

    public TypePlace getTypePlace() {
        return typePlace;
    }

    public void setTypePlace(TypePlace typePlace) {
        this.typePlace = typePlace;
    }
}

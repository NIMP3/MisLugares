package com.yovanydev.mislugares.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yovanydev.mislugares.R;
import com.yovanydev.mislugares.model.Place;
import com.yovanydev.mislugares.model.Places;
import com.yovanydev.mislugares.utilities.ToolbarUtility;

import java.io.File;
import java.io.FileNotFoundException;
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

    FloatingActionButton fabCamera;
    FloatingActionButton fabGallery;

    final static int EDIT_RESULT = 1;
    final static int GALLERY_RESULT = 2;
    final static int PHOTO_RESULT = 3;

    private long id;
    private Place place;
    private Uri uriPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id",-1);
        place = Places.searchPlace((int) id);

        ToolbarUtility.showToolbar(this,place.getTypePlace().getText(),false);

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
    * Comparte la URL del lugar*/
    private void sharePlace() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, place.getNamePlace() + "-" +place.getUrlPlace());
        startActivity(intent);
    }

    /*----------------------------------------------------------------------------------------------
    * Abre Google Maps con unas coordenadas determinadas por el lugar seleccionado*/
    private void openMap() {
        Uri uri;
        double latitude = place.getPositionPlace().getLatitude();
        double longitude = place.getPositionPlace().getLongitude();

        if (latitude != 0 || longitude != 0)
            uri = Uri.parse("geo:" + latitude + "," + longitude);
        else
            uri = Uri.parse("geo:0,0?q=" + place.getAddressPlace());

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    /*----------------------------------------------------------------------------------------------
    * Realiza una marcación del telefono del lugar seleccionado
    *
    * @param <code>View view</code> : Vista que solicita el metodo*/
    public void dialPhoneNumer(View view) {
        int phone = place.getPhonePlace();
        if (phone != 0)
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
    }

    /*----------------------------------------------------------------------------------------------
    * Abre la pagina del lugar seleccionado
    *
    * @param <code>View view</code> : Vista que solicita el metodo*/
    public void openUrl(View view) {
        String url = place.getUrlPlace();
        if (!url.equals(""))
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    /*----------------------------------------------------------------------------------------------
    * Abre la galeria de fotos para elegir una imágen
    *
    * @param <code>View view</code> : Vista que invoca el metodo*/
    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_RESULT);
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

    /*----------------------------------------------------------------------------------------------
    * Poner la nueva fotografia en Pantalla
    *
    * @param <code>ImageView imageView</code> : Vista para la fotografia
    * @param <code>String uri</code> : Ruta de la fotografia*/
    protected void putPhoto(ImageView imageView, String uri) {
        if (uri != null) {
            /*Picasso.get()
                    .load(uri)
                    .resize(1024,1024)
                    .error(R.drawable.place)
                    .centerCrop()
                    .into(imageView);*/
            imageViewPicturePlace.setImageBitmap(reduceBitmap(this,uri,1024,1024));
        }
        else {
            Picasso.get()
                    .load(R.drawable.place)
                    .into(imageView);
        }
    }

    /*----------------------------------------------------------------------------------------------
    * Toma una fotografia con la camara del celular
    *
    * @param <code>View view</code> : Vista que invoca al metodo*/
    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        uriPhoto = Uri.fromFile(new File(
                Environment.getExternalStorageDirectory() + File.separator
                + "img_" + (System.currentTimeMillis()/1000) + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
        startActivityForResult(intent, PHOTO_RESULT);
    }

    /*----------------------------------------------------------------------------------------------
    * Reduce el tamaño de una Imagen para optimizar su presentación
    *
    * @param context : Contexto en el que se aplican los cambios
    * @param uri : dirección unica de la foto
    * @param maxWidth : Ancho maximo para la foto
    * @param maxHeight : Alto maximo para la foto
    *
    * @return <code>Bitmap</code> : Imagen en un Bitmap con factor de reducción*/
    public static Bitmap reduceBitmap(Context context, String uri, int maxWidth, int maxHeight) {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            /*Evita decodificar toda la fotografía y cargarla en memoria
            * Limita a obtener sus dimensiones y almacenarlas en
            * <code>options.outWidth</code> - <code>options.outHeight</code>*/
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(Uri.parse(uri)),
                    null,
                    options);
            /*Calcula el factor de reducción deseado y lo almacena en
            * <code>options.inSampleSize</code>
            * Dividimos el ancho y alto real y los dividimos por los respectivos
            * maxancho y max alto, redondeamos hacia arriba
            * Seleccionamos el mayor de los dos
            * BitmapFactory trabaja con factores de reducción que sean potencias de dos
            *
            * Example: foto(4096x3072) [factor_reducción(4)] foto_reducción(1024x768)*/
            options.inSampleSize = (int) Math.max(
                    Math.ceil(options.outWidth / maxWidth),
                    Math.ceil(options.outHeight / maxHeight));
            /*Ahora requerimos decodificar toda la imagen por lo que desactivamos
            * <code>inJustDecodeBounds</code>*/
            options.inJustDecodeBounds = false;
            /*Decodificamos la nueva imagen con la opción <code>inSampleSize</code>
            * como factor de reducción*/
            return BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(Uri.parse(uri)),
                    null,
                    options);
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Fichero/Recurso no encontrado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
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
                sharePlace();
                return true;
            case R.id.item_arrive:
                openMap();
                return true;
            case R.id.item_edit:
                Intent intent = new Intent(this, PlaceEditActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,EDIT_RESULT);
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
        if (requestCode == EDIT_RESULT) updateDateView();
        else if (requestCode == GALLERY_RESULT && resultCode == RESULT_OK) {
            place.setPhotoPlace(data.getDataString());
            putPhoto(imageViewPicturePlace, place.getPhotoPlace());
        }
        else if (requestCode == PHOTO_RESULT && resultCode == RESULT_OK
                && place != null && uriPhoto != null) {
            place.setPhotoPlace(uriPhoto.toString());
            putPhoto(imageViewPicturePlace, place.getPhotoPlace());
        }
    }
}

package com.example.spot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;


public class Locationpage extends AppCompatActivity implements OnMapReadyCallback,  GoogleMap.OnPoiClickListener {

    private double latitue;
    private double longitude;
    private String locationName;
    private Button addLocationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationpage);

        addLocationButton = findViewById(R.id.addLocationButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        addLocationButton.setOnClickListener(v -> onAddLocationClick());
    }

    private void onAddLocationClick() {
        // here i want to go back to AddSpotPage
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selected_latitude", this.latitue);
        resultIntent.putExtra("selected_longitude", this.longitude);
        resultIntent.putExtra("selected_locationName", this.locationName);

        setResult(78, resultIntent);
        Log.d("trest", "bare testttt");
        finish();
        // code below copied from https://stackoverflow.com/questions/72634225/onbackpressed-is-deprecated-what-is-the-alternative
//        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                finish();
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setOnPoiClickListener(this);
        float zoomLevel = map.getMaxZoomLevel() - 5;
        LatLng uc3m = new LatLng(40.332690215919904, -3.765110001822394); // UC3M Leganés
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(uc3m, zoomLevel));
    }

    @Override
    public void onPoiClick(PointOfInterest poi) {

        latitue = poi.latLng.latitude;
        longitude = poi.latLng.longitude;
        locationName = poi.name;

        Toast.makeText(this, "Clicked: " + poi.name + "\nPlace ID:" + poi.placeId + "\nLatitude:" +
                        poi.latLng.latitude + " Longitude:" + poi.latLng.longitude, Toast.LENGTH_SHORT)
                .show();
    }
}
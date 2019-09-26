package com.nandaadisaputra.praktikum1mobileprogram.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.nandaadisaputra.praktikum1mobileprogram.R;

public class MapMainActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);

        // GET CURRENT LOCATION
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null){
                // Do it all with location
                Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                // Display in Toast
                Toast.makeText(MapMainActivity.this,
                        "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

package com.example.georgebrodsky.googlemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment fragmentMap;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the fragment in the UI:
        fragmentMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        // Will call this interface's method when the map is ready to use:
        fragmentMap.getMapAsync(this);
    }

    // Will be invoked when the map is ready to use:
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // We must ask this permission - user will see a dialog:
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else { //User's android version is Lower than Marshmallow or user gave us already a location permission:
            registerLocationUpdates();
        }
    }

    // Will be invoked when user allows or deny the permission in the dialog:
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != 1) {
            return;
        }

        // If user deny the permission:
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You can't use! Please allow the permission to continue!", Toast.LENGTH_SHORT).show();
        } else {
            registerLocationUpdates();
        }
    }
    // Start getting Location updates:
    private void registerLocationUpdates() {

    }
}

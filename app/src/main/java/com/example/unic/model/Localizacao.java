package com.example.unic.model;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

import java.util.List;

public class Localizacao implements LocationListener {

    public  double latitude ;
    public  double longitude  ;

    @Override
    public void onLocationChanged(@NonNull Location location) {

        this.longitude= location.getLongitude();
        this.latitude = location.getLatitude();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

package com.example.sriram.hikerswatch;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;

    LocationListener locationListener;

    TextView Latitude;

    TextView Longitude;

    TextView Accuracy;

    TextView Altitude;

    TextView Address;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Latitude = findViewById(R.id.Latitude);

        Longitude = findViewById(R.id.Longitude);

        Accuracy = findViewById(R.id.Accuracy);

        Altitude = findViewById(R.id.Altitude);

        Address = findViewById(R.id.Address);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String address = "";

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                Latitude.setText("Latitude: " + location.getLatitude());

                Longitude.setText("Longitude: " + location.getLongitude());

                Accuracy.setText("Accuracy: " + location.getAccuracy());

                Altitude.setText("Altitude: " + location.getAltitude());

                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addressList != null && addressList.size() > 0){
                        Log.i("info", addressList.get(0).toString());
                        if (addressList.get(0).getFeatureName() != null){
                            address += addressList.get(0).getFeatureName() + " ";
                        }
                        if (addressList.get(0).getThoroughfare() != null){
                            address += addressList.get(0).getThoroughfare() + " ";
                        }
                        if (addressList.get(0).getLocality() != null){
                            address += addressList.get(0).getLocality() + " ";
                        }
                        if (addressList.get(0).getSubAdminArea() != null){
                            address += addressList.get(0).getSubAdminArea() + " ";
                        }
                        if (addressList.get(0).getAdminArea() != null){
                            address += addressList.get(0).getAdminArea() + " ";
                        }
                        if (addressList.get(0).getPostalCode() != null){
                            address += addressList.get(0).getPostalCode();
                        }
                        Address.setText("Address: " + address);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,locationListener);
        }
    }
}

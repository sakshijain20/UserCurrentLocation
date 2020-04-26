package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView display_data;
    Button btn_location;

    LocationListener locationListener;
    LocationManager locationManager;
    Location location;


    boolean isGPSenabled=false;

    protected double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_location = (Button) findViewById(R.id.btn_location);
        display_data = (TextView) findViewById(R.id.display_data);

        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        try{
            Log.d("gpsTracker","In try block");
            if(isGPSenabled)
            {
                String provider = LocationManager.GPS_PROVIDER;
                location=new Location(provider);
                Log.d("gpsTracker","Location provided");
                location = locationManager.getLastKnownLocation(provider);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Log.d("gpsTracker","after request");
                if(location==null)
                {
                    Log.d("gpsTracker","Location is null!");
                }
                else{
                    Double latitude= location.getLatitude();
                    Double longitude = location.getLongitude();
                    Toast.makeText(this, "Latitude = " +latitude + "Longitude = " +longitude, Toast.LENGTH_SHORT).show();
                }

            }
        }
        catch(SecurityException e)
        {
            Log.d("gpsTracker","In catch block");
            e.printStackTrace();
        }


    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("gpsTracker","onLocation Changed");

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("gpsTracker","onProviderEnabled");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("gpsTracker","onProviderDisabled");
    }
}

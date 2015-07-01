package net.marcosnavarro.geoexample;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private LocationManager locationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                updatePosition(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    public void onClick(View v){
        Toast.makeText(this, getResources().getString(R.string.update), Toast.LENGTH_SHORT).show();
        updatePosition();
    }

    private void updatePosition(){
        Location loc = (Location) locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        updatePosition(loc);
    }

    private void updatePosition(Location location){
        if(location != null){
            TextView textView = (TextView) findViewById(R.id.textView);
            Toast.makeText(this, "new location", Toast.LENGTH_LONG).show();
            textView.setText("Latitude: "+location.getLatitude()+"\n"
                + "Longuitude: "+location.getLongitude());
        }else{
            Toast.makeText(this, "location variable is null", Toast.LENGTH_LONG).show();
        }
    }

}

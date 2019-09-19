package com.example.gmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText place;
    private Double lat_search, lon_search, dbllat, dbllng;
    private Float dblzoom;
    private EditText lat, lon, zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peta);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);

        Button go = findViewById(R.id.btnGo);
        go.setOnClickListener(op);

        place = findViewById(R.id.edSearch);
        Button search = findViewById(R.id.btnSearch);
        search.setOnClickListener(op);

        lat = findViewById(R.id.edLat);
        lon = findViewById(R.id.edLon);
        zoom = findViewById(R.id.edZoom);
    }

    private boolean check(EditText ttw){
        if(ttw.getText().toString().matches("")){
            Toast.makeText(MapsActivity.this, ttw.getHint().toString()+" is empty", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(zoom.getText().toString().matches("")){
                dblzoom = 10.0f;
                zoom.setText(String.valueOf(dblzoom));
            }
            else
                dblzoom = Float.parseFloat(zoom.getText().toString());

            if(check(lat) || check(lon))
                return;

            switch (v.getId()){
                case R.id.btnGo:
                    sembunyikanKeyBoard(v);
                    goToLocation();
                    break;

                case R.id.btnSearch:
                    goSearch();
                    break;
            }
        }
    };

    private void goSearch(){
        Geocoder g = new Geocoder(getBaseContext());

        try {
            List<android.location.Address> list = g.getFromLocationName(place.getText().toString(), 1);
            Address address = list.get(0);

            String addressFound = address.getAddressLine(0);
            lat_search = address.getLatitude();
            lon_search = address.getLongitude();

            Toast.makeText(this,"Move to "+addressFound+" Lat:" +lat_search+ " Long:" +lon_search,Toast.LENGTH_LONG).show();

            goToPeta(lat_search, lon_search, dblzoom);

            dbllat = Double.parseDouble(lat.getText().toString());
            dbllng = Double.parseDouble(lon.getText().toString());
            hitungJarak(dbllat, dbllng, lat_search, lon_search);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void hitungJarak(double latAsal, Double lngAsal, double latTujuan, double lngTujuan){
        Location asal = new Location("asal");
        Location tujuan = new Location("tujuan");
        tujuan.setLatitude(latTujuan);
        tujuan.setLatitude(lngTujuan);
        asal.setLatitude(latAsal);
        asal.setLongitude(lngAsal);
        float jarak = asal.distanceTo(tujuan)/1000;
        String jaraknya = String.valueOf(jarak);
        Toast.makeText(getBaseContext(), "Distance : " + jaraknya +" km ", Toast.LENGTH_LONG).show();
    }

    private void goToLocation(){
        dbllat = Double.parseDouble(lat.getText().toString());
        dbllng = Double.parseDouble(lon.getText().toString());

        lat.setText(String.valueOf(lat_search));
        lon.setText(String.valueOf(lon_search));

        Toast.makeText(this,"Move to Lat:" +dbllat + " Long:" +dbllng, Toast.LENGTH_LONG).show();
        goToPeta(dbllat, dbllng, dblzoom);
    }

    private void sembunyikanKeyBoard(View v){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    private void goToPeta(Double lat, Double lon, float zoom){
        LatLng newLoc = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(newLoc).title("Marker in  "+lat+":" +lon));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLoc, zoom));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ITS = new LatLng(-7.2819705,112.795323);
        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS, 15));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.hibryd:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.none:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

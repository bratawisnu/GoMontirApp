package com.example.montir.gomontirapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ucup on 7/11/2017.
 */

public class order_montirActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback {
    private ImageView icon_marker;

    String[] montirArray = {"Ridwan", "Ucup", "Mamot", "Febri",
            "Sera", "Solopok", "Dona", "Dicki"};

    private GoogleMap mMap;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_order);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.montir_listview, montirArray);

        ListView listView = (ListView) findViewById(R.id.lv_list_montir);
        listView.setAdapter(adapter);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);

        icon_marker = (ImageView) findViewById(R.id.iv_marker);
        icon_marker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), detail_montir.class));
                order_montirActivity.this.finish();
            }
        });

    }


    @Override
    public void onLocationChanged(Location location) {
        Log.e("TAG","Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), indexActivity.class));
        order_montirActivity.this.finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng bojongsoang = new LatLng(-6.9880413,107.6334169);
        mMap.addMarker(new MarkerOptions().position(bojongsoang).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bojongsoang));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bojongsoang, 12.0f));

    }

}

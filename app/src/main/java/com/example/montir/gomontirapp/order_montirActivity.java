package com.example.montir.gomontirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class order_montirActivity extends FragmentActivity implements OnMapReadyCallback {
    private ImageView icon_marker;

    String[] montirArray = {"Ridwan","Ucup","Mamot","Febri",
            "Sera","Solopok","Dona","Dicki"};

    private GoogleMap mMap;


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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}

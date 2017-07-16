package com.example.montir.gomontirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ucup on 7/11/2017.
 */

public class detail_montir extends AppCompatActivity {

    private ImageView photo;
    private TextView nama_montir, usia, alamat, pengalaman;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_montir);

        photo = (ImageView) findViewById(R.id.iv_profil_montir);

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), order_montirActivity.class));
        detail_montir.this.finish();
    }
}

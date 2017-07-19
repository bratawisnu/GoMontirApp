package com.example.montir.gomontirapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.montir.gomontirapp.config.SessionManager;
import com.example.montir.gomontirapp.config.config;

public class indexActivity extends AppCompatActivity {
    private RelativeLayout orderMontir, profil, lokasiToko, riwayat;
    private String id;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_index);
        orderMontir = (RelativeLayout) findViewById(R.id.rel_order_montir);
        profil      = (RelativeLayout) findViewById(R.id.rel_profil);
        lokasiToko  = (RelativeLayout) findViewById(R.id.rel_lokasi_toko);
        riwayat     = (RelativeLayout) findViewById(R.id.rel_riwayat);

        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        id = session.getId();
        users usr = new users();
        Utils.ShowToast("hello "+usr.getUsername());
        CheckUser();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);
        orderMontir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), order_montirActivity.class));
                indexActivity.this.finish();
            }
        });
    }

    private void CheckUser() {
        // TODO: 7/15/2017  check from database users
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        //startActivity(new Intent(getApplicationContext(), loginActivity.class));
        //indexActivity.this.finish();
        AskOption();

    }


    private void logoutUser() {

        session.clearSession();

        // Launching the login activity
        Intent intent = new Intent(indexActivity.this, loginActivity.class);
        startActivity(intent);
        finish();
    }


    private void AskOption()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(indexActivity.this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}

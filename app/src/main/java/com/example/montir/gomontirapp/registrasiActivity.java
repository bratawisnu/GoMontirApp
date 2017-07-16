package com.example.montir.gomontirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ucup on 7/8/2017.
 */

public class registrasiActivity extends AppCompatActivity {
    private EditText username, email, password, telepon, alamat;
    private Button btnRegistrasi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_registrasi);
        username      = (EditText)findViewById(R.id.et_reg_username);
        email         = (EditText)findViewById(R.id.et_reg_email);
        password      = (EditText)findViewById(R.id.et_reg_password);
        telepon       = (EditText)findViewById(R.id.et_reg_telpon);
        alamat        = (EditText)findViewById(R.id.et_reg_alamat);
        btnRegistrasi = (Button)findViewById(R.id.btnRegistrasi);

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), loginActivity.class));
                registrasiActivity.this.finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), loginActivity.class));
        registrasiActivity.this.finish();
    }
}

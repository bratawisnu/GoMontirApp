package com.example.montir.gomontirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ucup on 7/9/2017.
 */

public class sandiActivity extends AppCompatActivity {
    private EditText set_pwd;
    private EditText retype_pwd;
    private Button btnSubmit_pwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_sandi);
        set_pwd       = (EditText)findViewById(R.id.et_reset_password);
        retype_pwd    = (EditText)findViewById(R.id.et_confirm_password);
        btnSubmit_pwd = (Button)findViewById(R.id.btn_sandi);

        btnSubmit_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), loginActivity.class));
                sandiActivity.this.finish();
            }
        });
        
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), loginActivity.class));
        sandiActivity.this.finish();
    }
}

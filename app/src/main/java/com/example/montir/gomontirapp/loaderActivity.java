package com.example.montir.gomontirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by ucup on 7/8/2017.
 */

public class loaderActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_loader);

//        getActionBar().hide();
        Thread loading=new Thread(new Runnable(){

            int i=0;
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true){
                    try {
                        i++;
                        Thread.sleep(1000);
                        if (i==3){
                            break;
                        }
                    } catch (Exception e) {
                        // TODO: handle exception

                    }
                }
                startActivity(new Intent(getApplicationContext(), loginActivity.class));
                loaderActivity.this.finish();
            }

        });
        loading.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;



}
}

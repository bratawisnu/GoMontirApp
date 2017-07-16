package com.example.montir.gomontirapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.montir.gomontirapp.config.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ucup on 7/8/2017.
 */

public class loginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView btnDaftar, btnLupaSandi;
    private EditText et_username, et_password;

    private boolean loggedIn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_login);
        btnLogin     = (Button) findViewById(R.id.btnLogin);
        btnDaftar    = (TextView) findViewById(R.id.tv_daftar);
        btnLupaSandi = (TextView) findViewById(R.id.tv_lupasandi);
        et_username     = (EditText) findViewById(R.id.et_username);
        et_password     = (EditText) findViewById(R.id.et_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //if (checkLogin())
                //    new JSONParser().execute("");
                login();
//                startActivity(new Intent(getApplicationContext(), indexActivity.class));
//                loginActivity.this.finish();
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), registrasiActivity.class));
                loginActivity.this.finish();
            }
        });

        btnLupaSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), sandiActivity.class));
                loginActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(loginActivity.this, indexActivity.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String email = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        Log.e("TAG","user : "+email);
        //Creating a string request
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(config.LOGIN_SUCCESS)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = loginActivity.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(config.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(loginActivity.this, indexActivity.class);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(loginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(config.KEY_EMAIL, email);
                params.put(config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }

        };

        Log.e("TAG","string response : "+stringRequest);
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        AskOption();
    }

    private void AskOption()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);

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
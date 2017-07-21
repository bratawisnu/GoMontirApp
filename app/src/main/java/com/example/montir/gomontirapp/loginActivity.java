package com.example.montir.gomontirapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.montir.gomontirapp.config.SessionManager;
import com.example.montir.gomontirapp.config.config;
import com.example.montir.gomontirapp.tools.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ucup on 7/8/2017.
 */

public class loginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView btnDaftar, btnLupaSandi;
    private EditText et_username, et_password;
    private String email, password, id, username, telepon, getEmail, getPassword ;
    String myJSON;
    JSONObject json = null;
    private String TAG = "Debug Test";

    SessionManager session;
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
                getEmail = et_username.getText().toString().trim();
                getPassword = et_password.getText().toString().trim();
                if (checkLogin())
                    new GetContacts().execute();

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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.ShowToast("Json Data is downloading");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String url = config.LOGIN_URL+"?email="+getEmail+"&&password="+getPassword;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("TAG", "Response from url: " +jsonStr);
            if (jsonStr != null) {
                Log.e("TAG", "Response jsonstr");
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    JSONObject jsonObj = jsonArray.getJSONObject(0);
                    // Getting Array node
                    id = jsonObj.getString("id");
                    email = jsonObj.getString("email");
                    password = jsonObj.getString("password");
                    username = jsonObj.getString("username");
                    telepon = jsonObj.getString("telepon");

                    users usr = new users();
                    usr.setUid(id);
                    usr.setEmail(email);
                    usr.setUsername(username);
                    usr.setTelepon(telepon);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            session = new SessionManager(getApplicationContext());
            Log.e("TAG","log :"+getEmail + "emaillls :"+email );
            if (email.equals(getEmail)&& password.equals(getPassword)) {

                session.setLogin(id, true, "id");

                Intent in = new Intent(getApplicationContext(), indexActivity.class);
                in.putExtra("id", id);
                startActivity(in);

            } else {
                Toast.makeText(getApplicationContext(), "Login gagal!!",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean checkLogin(){
        boolean logiin = false;

        getEmail = et_username.getText().toString();
        getPassword = et_password.getText().toString();

        if (getEmail.isEmpty() || !isValidEmail(getEmail)) {
            et_username.setError("Please Enter Valid Email Address!");
        }

        if (getPassword.isEmpty()) {
            et_password.setError("Field cant be blank!");
        }

        if (!getEmail.isEmpty() && isValidEmail(getEmail) && !getPassword.isEmpty()){
            logiin = true;
        }

        return logiin;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
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
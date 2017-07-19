package com.example.montir.gomontirapp.config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.montir.gomontirapp.loginActivity;

/**
 * Created by ucup on 7/19/2017.
 */

public class SessionManager {

    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "FDeliveryLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_ID = "idpelanggan";
    private static final String KEY_STATUS_LOGIN = "statusLogin";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(String id, boolean isLoggedIn, String statusLogin) {

        editor.putString(KEY_ID, id);
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_STATUS_LOGIN, statusLogin);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getId(){
        return pref.getString(KEY_ID, null);
    }

    public String getStatusLogin(){
        return pref.getString(KEY_STATUS_LOGIN, null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, loginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public void clearSession(){
        editor.clear();
        editor.commit();
    }
}

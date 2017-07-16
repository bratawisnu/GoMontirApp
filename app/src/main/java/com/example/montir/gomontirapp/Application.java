package com.example.montir.gomontirapp;

import android.content.Context;


/**
 * Created by lukes on 11/14/16.
 */

public class Application extends android.app.Application {

    private static Context _context;
    @Override
    public void onCreate()
    {
        super.onCreate();

        _context = getApplicationContext();

        // Init loader
//        BIGAImageLoader.Init(this);
//
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//
//        // Change default font
//        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Roboto-Regular.ttf");
//        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Roboto-Regular.ttf");
//        FontsOverride.setDefaultFont(this, "SERIF", "fonts/Roboto-Regular.ttf");
//        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Roboto-Regular.ttf");
    }

    public static Context GetApplicaionContext ()
    {
        return _context;
    }
}

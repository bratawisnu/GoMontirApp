package com.example.montir.gomontirapp;

import android.widget.Toast;

/**
 * Created by ucup on 7/15/2017.
 */

public class Utils {
    public static void ShowToast(String s) {
        Toast.makeText(Application.GetApplicaionContext(), s, Toast.LENGTH_SHORT).show();
    }
}

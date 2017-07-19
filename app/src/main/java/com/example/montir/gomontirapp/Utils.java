package com.example.montir.gomontirapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by ucup on 7/15/2017.
 */

public class Utils {
    public static void ShowToast(String s) {
        Toast.makeText(Application.GetApplicaionContext(), s, Toast.LENGTH_SHORT).show();
    }
}

package com.example.montir.gomontirapp;

/**
 * Created by ucup on 7/15/2017.
 */

public class users {
    private static String uid, username, email, telepon, alamat, usia, photo;
    public users ()
    {

    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        users.uid = uid;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        users.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        users.email = email;
    }

    public static String getTelepon() {
        return telepon;
    }

    public static void setTelepon(String telepon) {
        users.telepon = telepon;
    }

    public static String getAlamat() {
        return alamat;
    }

    public static void setAlamat(String alamat) {
        users.alamat = alamat;
    }

    public static String getUsia() {
        return usia;
    }

    public static void setUsia(String usia) {
        users.usia = usia;
    }

    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        users.photo = photo;
    }
}

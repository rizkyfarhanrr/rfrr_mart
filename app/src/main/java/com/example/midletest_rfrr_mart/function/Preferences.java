package com.example.midletest_rfrr_mart.function;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    //simpan sesi login user

    static final String KEY_USER_TERDAFTAR = "username_ref";
    static final String KEY_PASS_TERDAFTAR = "password_ref";
    static final String KEY_CODE_TERDAFTAR = "password_ref";

    static final String KEY_USER_IS_LOGIN = "username_loggedin";
    static final String KEY_STATUS_IS_LOGIN = "status_username_loggedin";

    private static SharedPreferences getSharedPreferences (Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // TODO 1 : Preferensi untuk User Register
    public static void setregistereduser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USER_TERDAFTAR, username);
        editor.apply();
    }

    public static String getregistereduser(Context context) {
        return getSharedPreferences(context).getString(KEY_USER_TERDAFTAR, "");
    }

    // TODO 2 : Preferensi untuk Password User Terdaftar
    public static void setregisteredpass(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USER_TERDAFTAR, password);
        editor.apply();
    }

    public static String getregisteredpass(Context context) {
        return getSharedPreferences(context).getString(KEY_PASS_TERDAFTAR, "");
    }

    // TODO 3 : Preferensi untuk Password User Terdaftar
    public static void setregisteredCode(Context context, String code) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_CODE_TERDAFTAR, code);
        editor.apply();
    }

    public static String getregisteredcode(Context context) {
        return getSharedPreferences(context).getString(KEY_CODE_TERDAFTAR, "");
    }

    // TODO 4 : Preferensi untuk Posisi User yang sedang Login
    public static void setloginuser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USER_IS_LOGIN, username);
        editor.apply();
    }

    public static String getloginuser(Context context) {
        return getSharedPreferences(context).getString(KEY_USER_TERDAFTAR,"");
    }

    // TODO 5 : Preferensi untuk Login status
    public static void setloginuserstatus(Context context, Boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(KEY_STATUS_IS_LOGIN, status);
        editor.apply();
    }

    public static Boolean getloginuserstatus(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_STATUS_IS_LOGIN,  false);
    }

    // TODO 6 : Clear Preferensi Login (Logout)
    public static void clearloggedinuser(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_USER_IS_LOGIN);
        editor.remove(KEY_STATUS_IS_LOGIN);
        editor.apply();
    }

}
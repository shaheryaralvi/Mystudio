package com.mian.MZ;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPrefLocalStorage {

    public static final String MY_PREF = "A";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public MyPrefLocalStorage(Context context) {

        sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

}

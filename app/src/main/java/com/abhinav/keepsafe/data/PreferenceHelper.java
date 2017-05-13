package com.abhinav.keepsafe.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.abhinav.keepsafe.KeepSafe;

/**
 * Created by Abhinav on 23/04/17.
 */
public class PreferenceHelper implements IPreferenceHelper {


    public static final String NAME = "keep_safe_prefs";
    private static final String USER_PIN = "user_pin";
    private final SharedPreferences sharedPreferences;

    protected PreferenceHelper() {
        sharedPreferences = KeepSafe.getInstance().getSharedPreferences(PreferenceHelper.NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveUserPin(String pin) {
        sharedPreferences.edit().putString(USER_PIN, pin).apply();
    }

    @Override
    public String getUserPin() {
        return sharedPreferences.getString(USER_PIN, null);
    }
}

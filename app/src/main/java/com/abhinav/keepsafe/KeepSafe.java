package com.abhinav.keepsafe;

import android.app.Application;

/**
 * Created by Abhinav on 07/05/17.
 */
public class KeepSafe extends Application {

    private static KeepSafe instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static KeepSafe getInstance() {
        return instance;
    }
}

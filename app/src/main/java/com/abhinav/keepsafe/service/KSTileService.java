package com.abhinav.keepsafe.service;

import android.os.Build;
import android.service.quicksettings.TileService;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by abhinav.sharma on 27/12/17.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class KSTileService extends TileService {
    private static final String TAG = "KSTileService";

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Log.d(TAG, "onTileAdded: ");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.d(TAG, "onTileRemoved: ");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Log.d(TAG, "onStartListening: ");
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.d(TAG, "onStopListening: ");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.d(TAG, "onClick: ");
    }
}

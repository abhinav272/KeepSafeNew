package com.abhinav.keepsafe.service;

import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.abhinav.keepsafe.R;

/**
 * Created by abhinav.sharma on 27/12/17.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class KSTileService extends TileService {
    private static final String TAG = "KSTileService";
    private static boolean isEnabled = false;

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
        updateKSTile();
    }

    private void updateKSTile() {
        Tile tile = getQsTile();
        if (isEnabled) {
            tile.setIcon(Icon.createWithResource(this,
                    R.drawable.ic_keepsafe_tile_disabled));
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            tile.setIcon(Icon.createWithResource(this,
                    R.drawable.ic_keepsafe_tile_enabled));
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.d(TAG, "onStopListening: ");
    }

    @Override
    public void onClick() {
        super.onClick();
        isEnabled = !isEnabled;
        updateKSTile();
    }
}

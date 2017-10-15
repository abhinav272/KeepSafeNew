package com.abhinav.keepsafe.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;

/**
 * Created by abhinav.sharma on 14/10/17.
 */

public class SplashFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}

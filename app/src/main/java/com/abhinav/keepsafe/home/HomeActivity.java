package com.abhinav.keepsafe.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.R;

/**
 * Created by Abhinav on 13/05/17.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addFragment(getFragmentManager(), new HomeFragment(), R.id.frame_container, HomeFragment.class.getSimpleName());
    }
}

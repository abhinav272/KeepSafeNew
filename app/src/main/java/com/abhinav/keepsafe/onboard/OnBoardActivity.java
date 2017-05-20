package com.abhinav.keepsafe.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.login.LoginFragment;

/**
 * Created by Abhinav on 23/04/17.
 */
public class OnBoardActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        addFragment(getFragmentManager(), new LoginFragment(), R.id.frame_container, LoginFragment.class.getSimpleName());
    }
}

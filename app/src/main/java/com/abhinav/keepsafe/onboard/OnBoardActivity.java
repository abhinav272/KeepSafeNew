package com.abhinav.keepsafe.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.login.LoginFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Abhinav on 23/04/17.
 */
public class OnBoardActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        addFragment(getFragmentManager(), new SplashFragment(), R.id.frame_container,
                SplashFragment.class.getSimpleName());
        showSplash();
    }

    private void showSplash() {
        Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> launchPinView());

    }

    private void launchPinView() {
        addFragment(getFragmentManager(), new LoginFragment(),
                R.id.frame_container, LoginFragment.class.getSimpleName());
    }
}

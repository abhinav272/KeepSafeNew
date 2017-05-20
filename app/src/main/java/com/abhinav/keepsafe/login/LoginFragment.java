package com.abhinav.keepsafe.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.home.HomeActivity;
import com.abhinav.keepsafe.navigation.NavigationRequest;
import com.abhinav.keepsafe.navigation.Navigator;
import com.abhinav.keepsafe.onboard.OnBoardActivity;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

/**
 * Created by Abhinav on 23/04/17.
 */
public class LoginFragment extends BaseFragment implements LoginContract.IView {

    private LoginContract.IPresenter mPresenter;

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private TextView tvBanner;
    private String pin1, pin2;
    private OnBoardActivity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new LoginPresenter();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setupLockView(view);
    }

    private void initView(View view) {
        tvBanner = (TextView) view.findViewById(R.id.tv_banner);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.IPresenter presenter) {
        mPresenter = checkNotNull(presenter);
        tvBanner.setText(getString(R.string.choose_pin_string));
    }

    @Override
    public void showLoginSuccessMessage() {
        Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPinSavedSuccessfullyMessage() {
        mPresenter.saveNewPin(pin1);
        Toast.makeText(context, getString(R.string.pin_saved_successfully), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidPinMessage() {
        mPinLockView.resetPinLockView();
        Toast.makeText(context, getString(R.string.invalid_pin_msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPinMismatchMessage() {
        Toast.makeText(context, getString(R.string.pin_mismatch), Toast.LENGTH_SHORT).show();
        mPinLockView.resetPinLockView();
        pin1 = pin2 = null;
        tvBanner.setText(getString(R.string.choose_pin_string));
    }

    @Override
    public void setupLockView(View view) {
        mPinLockView = (PinLockView) view.findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) view.findViewById(R.id.indicator_dots);
        tvBanner.setText(getString(R.string.choose_pin_string));
        if (mIndicatorDots != null) {
            mIndicatorDots.setPinLength(4);
        }
        mPinLockView.attachIndicatorDots(mIndicatorDots);

        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.e("onComplete: ", pin);
                if(!mPresenter.isAlreadyOnBoarded()){
                    if (pin1 == null) {
                        pin1 = pin;
                        tvBanner.setText(R.string.confirm_pin_string);
                        mPinLockView.resetPinLockView();
                    } else{
                        pin2 = pin;
                        if(pin1.equals(pin2)){
                            showPinSavedSuccessfullyMessage();
                            navigateToHome();
                        } else {
                            showPinMismatchMessage();
                        }
                    }
                } else {
                    if(mPresenter.validatePin(pin)){
                        showLoginSuccessMessage();
                        navigateToHome();
                    }
                    else {
                        showInvalidPinMessage();
                    }
                }
            }

            @Override
            public void onEmpty() {
                Log.e("onEmpty: ", "");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.e("onPinChange: ", intermediatePin + "  " + pinLength);
            }
        });
    }

    @Override
    public void navigateToHome() {
        NavigationRequest navigateToHomeRequest = new NavigationRequest.Builder()
                .setNavigateTo(Constants.NavigateTo.HOME)
                .setLaunchedBy(Constants.LaunchedBy.LOGIN).build();
        Navigator.navigateTo(context, navigateToHomeRequest);
        context.finish();
    }
}

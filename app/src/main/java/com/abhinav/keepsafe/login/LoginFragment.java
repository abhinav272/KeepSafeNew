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
import com.abhinav.keepsafe.navigation.NavigationRequest;
import com.abhinav.keepsafe.navigation.Navigator;
import com.abhinav.keepsafe.onboard.OnBoardActivity;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

/**
 * Created by Abhinav on 23/04/17.
 */
public class LoginFragment extends BaseFragment implements LoginView {

    private LoginPresenter mPresenter;
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private TextView tvBanner;
    private OnBoardActivity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new LoginPresenter(this);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLockView(view);
        mPresenter.initView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void setupLockView(View view) {
        tvBanner = (TextView) view.findViewById(R.id.tv_banner);
        mPinLockView = (PinLockView) view.findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) view.findViewById(R.id.indicator_dots);

        if (mIndicatorDots != null) {
            mIndicatorDots.setPinLength(4);
        }
        mPinLockView.attachIndicatorDots(mIndicatorDots);

        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.e("onComplete: ", pin);
                mPresenter.onPinEntered(pin);
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
    public void setNewPinBannerText() {
        tvBanner.setText(getString(R.string.choose_pin_string));
    }

    @Override
    public void setPinBannerText() {
        tvBanner.setText(getString(R.string.enter_pin_string));
    }

    @Override
    public void setPinMismatchBannerText() {
        Toast.makeText(context, getString(R.string.pin_mismatch), Toast.LENGTH_SHORT).show();
        mPinLockView.resetPinLockView();
        tvBanner.setText(getString(R.string.choose_pin_string));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        NavigationRequest navigateToHomeRequest = new NavigationRequest.Builder()
                .setNavigateTo(Constants.NavigateTo.HOME)
                .setLaunchedBy(Constants.LaunchedBy.LOGIN).build();
        Navigator.navigateTo(context, navigateToHomeRequest);
        context.finish();
    }

    @Override
    public void setInvalidPinBannerText() {
        tvBanner.setText(getString(R.string.enter_pin_string));
        mPinLockView.resetPinLockView();
        Toast.makeText(context, getString(R.string.invalid_pin_msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetPinViewOnFirstEntry() {
        tvBanner.setText(getString(R.string.confirm_pin_string));
        mPinLockView.resetPinLockView();
    }
}

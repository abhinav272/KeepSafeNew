package com.abhinav.keepsafe.login;

import android.view.View;

import com.abhinav.keepsafe.base.IBaseView;

/**
 * Created by abhinav.sharma on 09/10/17.
 */

public interface LoginView extends IBaseView {

    void setupLockView(View layout);
    void setNewPinBannerText();
    void setPinBannerText();
    void setPinMismatchBannerText();
    void navigateToHome();
    void setInvalidPinBannerText();
    void resetPinViewOnFirstEntry();
}

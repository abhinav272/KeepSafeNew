package com.abhinav.keepsafe.login;

import com.abhinav.keepsafe.base.BasePresenter;

/**
 * Created by Abhinav on 07/05/17.
 */
public class LoginPresenter extends BasePresenter<LoginView> implements LoginModelListener {

    private LoginModel loginModel;
    private String firstPinEntry;


    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    public void setModel() {
        loginModel = new LoginModel(this);
    }

    @Override
    public void destroy() {
        loginModel.detachListener();
        loginModel = null;
    }

    @Override
    public void initView() {
        if (!isAlreadyOnBoarded()) {
            getView().setNewPinBannerText();
        } else
            getView().setPinBannerText();
    }

    protected void setNewPin(String newPin) {
        loginModel.dataManager.savePin(newPin);
    }

    protected void onPinEntered(String pin) {
        if (isAlreadyOnBoarded()) {
            if (validatePin(pin)) {
                navigateToHome();
            } else {
                onInvalidPin();
            }
        } else {
            if (firstPinEntry == null) {
                firstPinEntry = pin;
                resetPinView();
            } else if (firstPinEntry.equals(pin)) {
                setNewPin(pin);
                navigateToHome();
            } else {
                firstPinEntry = null;
                onPinMismatch();
            }
        }
    }

    private void resetPinView() {
        getView().resetPinViewOnFirstEntry();
    }

    private void onInvalidPin() {
        getView().setInvalidPinBannerText();
    }

    private void onPinMismatch() {
        getView().setPinMismatchBannerText();
    }

    private void navigateToHome() {
        getView().navigateToHome();
    }

    public boolean validatePin(String pin) {
        return pin.equals(loginModel.dataManager.getSavedPin());
    }

    public boolean isAlreadyOnBoarded() {
        return loginModel.dataManager.getSavedPin() != null;
    }


}

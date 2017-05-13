package com.abhinav.keepsafe.login;

import com.abhinav.keepsafe.data.DataManager;

/**
 * Created by Abhinav on 07/05/17.
 */
public class LoginPresenter implements LoginContract.IPresenter {

    private DataManager mDataManager;

    public LoginPresenter() {
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void saveNewPin(String pin) {
        mDataManager.savePin(pin);
    }

    @Override
    public boolean validatePin(String pin) {
        return pin.equals(mDataManager.getSavedPin());
    }

    @Override
    public boolean isAlreadyOnBoarded() {
        return mDataManager.getSavedPin() != null;
    }
}

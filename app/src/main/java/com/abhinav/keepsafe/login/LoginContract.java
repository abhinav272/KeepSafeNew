package com.abhinav.keepsafe.login;

import com.abhinav.keepsafe.IBasePresenter;
import com.abhinav.keepsafe.IBaseView;

/**
 * Created by Abhinav on 23/04/17.
 */
public interface LoginContract {

    interface IView extends IBaseView<IPresenter> {
        void showLoginSuccessMessage();
        void showInvalidPinMessage();
        void setupLockView(android.view.View view);
        void showPinMismatchMessage();
        void navigateToHome();
        void showPinSavedSuccessfullyMessage();
    }

    interface IPresenter extends IBasePresenter {
        void saveNewPin(String pin);
        boolean validatePin(String pin);
        boolean isAlreadyOnBoarded();
    }
}

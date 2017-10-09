package com.abhinav.keepsafe.base;

/**
 * Created by abhinav.sharma on 09/10/17.
 */

/**
* This contains all the generic methods which are common to most views
 * eg: Show any Toast or display loader etc.
* */
public interface IBaseView {

    void showSnackbar(String msg);

    void showToastLong(String msg);

    void showToastShort(String msg);

    void showProgressDialog();

    void hideProgressDialog();

//    /**
//     * perform UI action on data failure response
//     * @param failureResponse
//     */
//    void showError(FailureResponse failureResponse);
}

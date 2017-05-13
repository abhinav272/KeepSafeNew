package com.abhinav.keepsafe.data;

/**
 * Created by Abhinav on 23/04/17.
 */
public interface IPreferenceHelper {

    void saveUserPin(String pin);
    String getUserPin();

}

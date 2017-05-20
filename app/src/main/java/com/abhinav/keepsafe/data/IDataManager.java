package com.abhinav.keepsafe.data;

/**
 * Created by Abhinav on 23/04/17.
 */
public interface IDataManager {

    void savePin(String pin);
    String getSavedPin();

    void fetchAllCategories();
}

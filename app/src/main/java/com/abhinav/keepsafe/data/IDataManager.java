package com.abhinav.keepsafe.data;

import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 23/04/17.
 */
public interface IDataManager {

    void savePin(String pin);
    String getSavedPin();

    List<Category> fetchAllCategories();
}

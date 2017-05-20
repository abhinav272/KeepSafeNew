package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.data.DataManager;

/**
 * Created by Abhinav on 20/05/17.
 */
public class HomePresenter implements HomeContract.IPresenter {

    DataManager mDataManager;

    public HomePresenter() {
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchAllCategories() {
        mDataManager.fetchAllCategories();
    }

    @Override
    public void addNewCategory() {

    }

    @Override
    public void fetchCategoriesListing() {

    }
}

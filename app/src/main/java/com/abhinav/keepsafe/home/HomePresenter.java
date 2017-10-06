package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.data.DataManager;
import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

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
    public List<Category> fetchAllCategories() {
        return mDataManager.fetchAllCategories();
    }
}

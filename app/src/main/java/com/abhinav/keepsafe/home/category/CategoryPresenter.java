package com.abhinav.keepsafe.home.category;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> implements CategoryModelListener, LifecycleOwner {

    CategoryModel categoryModel;


    public CategoryPresenter(CategoryView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        categoryModel = new CategoryModel(this);
    }

    @Override
    protected void destroy() {
        categoryModel.detachListener();
        categoryModel = null;
    }

    @Override
    protected void initView() {

    }

    public void fetchCategoryListing(int position) {
        switch (position) {
            case -1:
                getView().popFragmentOnInvalidChoice();
                break;
            case 0:
                categoryModel.dataManager.fetchAllBanks().observe(this, banks -> {
                    getView().showBankListing(banks);
                });
                break;
            case 1:
                categoryModel.dataManager.fetchAllEmails();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public Lifecycle getLifecycle() {
        return new Lifecycle() {
            @Override
            public void addObserver(LifecycleObserver observer) {
                Log.d("Test", "addObserver: ");
            }

            @Override
            public void removeObserver(LifecycleObserver observer) {
                Log.d("Test", "removeObserver: ");
            }

            @Override
            public State getCurrentState() {
                return State.RESUMED;
            }
        };
    }
}

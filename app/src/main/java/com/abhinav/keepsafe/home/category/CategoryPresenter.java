package com.abhinav.keepsafe.home.category;

import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;

import java.util.List;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> implements CategoryModelListener {

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
                List<Bank> value = categoryModel.dataManager.fetchAllBanks().getValue();
                if (value != null && value.size() > 0) {
                    getView().showBankListing(value);
                } else {
                    getView().showNoItemView();
                }
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
}

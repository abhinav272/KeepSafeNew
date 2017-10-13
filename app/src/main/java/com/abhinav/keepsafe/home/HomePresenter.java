package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.data.DataManager;
import com.abhinav.keepsafe.pojo.Category;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Abhinav on 20/05/17.
 */
public class HomePresenter extends BasePresenter<HomeView> implements HomeModelListener {

    HomeModel homeModel;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        homeModel = new HomeModel(this);
    }

    @Override
    protected void destroy() {
        homeModel.detachListener();
        homeModel = null;
    }

    @Override
    protected void initView() {
        getView().showAllCTAs(fetchAllCategories());
    }

    public List<String> fetchAllCategories() {
        return Arrays.asList("BANK", "EMAIL", "SOCIAL NETWORK", "E-COMMERCE", "OTHERS");
    }

    public void showCategoryFragment(int position) {
        getView().navigateToCategoryFragment(position);
    }

    public void onAddBankClicked() {
        getView().showAddBankFragment();
    }

    public void onAddEmailClicked() {
        getView().showAddEmailFragment();
    }

    public void onAddSocialNetworkClicked() {

    }

    public void onAddECommerceClicked() {

    }

    public void onAddOthersClicked() {

    }
}

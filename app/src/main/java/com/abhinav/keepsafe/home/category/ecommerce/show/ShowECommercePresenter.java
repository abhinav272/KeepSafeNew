package com.abhinav.keepsafe.home.category.ecommerce.show;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.ECommerce;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

public class ShowECommercePresenter extends BasePresenter<ShowECommerceView> implements BaseModelListener {

    private ShowECommerceModel model;
    private Observer<ECommerce> eCommerceObserver;
    private LiveData<ECommerce> eCommerceLiveData;

    public ShowECommercePresenter(ShowECommerceView view) {
        super(view);
        eCommerceObserver = eCommerce -> {
            if (eCommerce != null) {
                getView().showECommerceDetails(eCommerce);
            } else {
                getView().popFragment();
            }
        };
    }

    @Override
    protected void setModel() {
        model = new ShowECommerceModel(this);
    }

    @Override
    protected void destroy() {
        eCommerceLiveData.removeObserver(eCommerceObserver);
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    void onEditClicked(int eCommerceId) {
        getView().showEditECommerceFragment(eCommerceId);
    }

    void fetchECommerceDetails(int eCommerceId) {
        eCommerceLiveData = model.dataManager.fetchECommerceDetails(eCommerceId);
        eCommerceLiveData.observeForever(eCommerceObserver);
    }
}

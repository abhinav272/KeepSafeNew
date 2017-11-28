package com.abhinav.keepsafe.home.category.ecommerce.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.ECommerce;

/**
 * Created by abhinav.sharma on 23/10/17.
 */

class EditECommercePresenter extends BasePresenter<EditECommerceView> implements BaseModelListener {

    private EditECommerceModel model;
    private LiveData<ECommerce> eCommerceLiveData;
    private Observer<ECommerce> eCommerceObserver;

    public EditECommercePresenter(EditECommerceView view) {
        super(view);
        eCommerceObserver = eCommerce -> {
            if (eCommerce != null)
                getView().showECommerceDetails(eCommerce);
            else {
                getView().popFragment();
            }
        };
    }

    @Override
    protected void setModel() {
        model = new EditECommerceModel(this);
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

    protected void fetchECommerceDetails(int eCommerceId) {
        eCommerceLiveData = model.dataManager.fetchECommerceDetails(eCommerceId);
        eCommerceLiveData.observeForever(eCommerceObserver);

    }

    protected void updateECommerceAccount(ECommerce eCommerce) {
        model.dataManager.updateECommerce(eCommerce);
        getView().popFragment();
    }

    protected void deleteECommerceAccount(ECommerce eCommerce) {
        model.dataManager.deleteECommerce(eCommerce);
        getView().popFragment();
    }
}

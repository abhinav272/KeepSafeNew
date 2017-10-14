package com.abhinav.keepsafe.home.category.ecommerce;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.ECommerce;

/**
 * Created by abhinav.sharma on 14/10/17.
 */

public class AddECommercePresenter extends BasePresenter<AddECommerceView> implements BaseModelListener {

    private AddECommerceModel model;

    public AddECommercePresenter(AddECommerceView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new AddECommerceModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void addECommerce(ECommerce eCommerce) {
        model.dataManager.addECommerce(eCommerce).subscribe(aLong -> {
            getView().showToastLong("ECommerce Account Added Successfully");
            getView().popFragment();
        });
    }
}

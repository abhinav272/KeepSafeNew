package com.abhinav.keepsafe.home.category.email;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Email;

import io.reactivex.functions.Consumer;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class AddEmailPresenter extends BasePresenter<AddEmailView> implements BaseModelListener {

    private AddEmailModel model;

    public AddEmailPresenter(AddEmailView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new AddEmailModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void addEmail(Email email){
        model.dataManager.addEmail(email).subscribe(aLong -> {
            getView().showToastShort("Email Added Successfully");
            getView().popFragment();
        });
    }
}

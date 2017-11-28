package com.abhinav.keepsafe.home.category.email.show;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Email;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

class ShowEmailPresenter extends BasePresenter<ShowEmailView> implements BaseModelListener {

    private ShowEMailModel model;
    private Observer<Email> emailObserver;
    private LiveData<Email> emailLiveData;

    public ShowEmailPresenter(ShowEmailView view) {
        super(view);
        emailObserver = email -> {
            if (email != null) {
                getView().showEmailDetails(email);
            } else {
                getView().popFragment();
            }
        };
    }

    @Override
    protected void setModel() {
        model = new ShowEMailModel(this);
    }

    @Override
    protected void destroy() {
        emailLiveData.removeObserver(emailObserver);
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    void onEditClicked(int emailId) {
        getView().showEditEmailFragment(emailId);
    }

    void fetchEmailDetails(int emailId) {
        emailLiveData = model.dataManager.fetchEmailDetails(emailId);
        emailLiveData.observeForever(emailObserver);
    }
}

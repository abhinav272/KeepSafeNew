package com.abhinav.keepsafe.home.category.email;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Email;

/**
 * Created by abhinav.sharma on 21/10/17.
 */

class EditEmailPresenter extends BasePresenter<EditEmailView> implements BaseModelListener{

    private EditEmailModel model;
    private LiveData<Email> emailLiveData;
    private Observer<Email> emailObserver;

    public EditEmailPresenter(EditEmailView view) {
        super(view);
        emailObserver = email -> {
            if (email != null) {
                getView().showEmailDetails(email);
            } else {
                getView().showToastShort("Unable to fetch Email Account");
            }
        };
    }

    @Override
    protected void setModel() {
        model = new EditEmailModel(this);
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

    protected void fetchEmailDetails(int emailId) {
        emailLiveData = model.dataManager.fetchEmailDetails(emailId);
        emailLiveData.observeForever(emailObserver);
    }

    protected void onSaveClicked(Email email) {
        model.dataManager.updateEmail(email);
        getView().popFragment();
    }

    protected void onDeleteClicked(Email email) {
        model.dataManager.deleteEmail(email);
        getView().popFragment();
    }
}

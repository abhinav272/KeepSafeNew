package com.abhinav.keepsafe.home.category.bank.show;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 24/11/17.
 */

public class ShowBankPresenter extends BasePresenter<ShowBankView> implements BaseModelListener {

    private ShowBankModel model;
    private LiveData<Bank> bankLiveData;
    private Observer<Bank> bankObserver;

    public ShowBankPresenter(ShowBankView view) {
        super(view);
        bankObserver = bank -> {
            if (bank != null) {
                getView().showBankDetails(bank);
            } else {
                getView().popFragment();
            }
        };
    }

    @Override
    protected void setModel() {
        model = new ShowBankModel(this);
    }

    @Override
    protected void destroy() {
        bankLiveData.removeObserver(bankObserver);
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void fetchBankDetails(int bankId) {
        bankLiveData = model.dataManager.fetchBankDetails(bankId);
        bankLiveData.observeForever(bankObserver);
    }

    public void onEditClicked(int bankId) {
        getView().showEditBankFragment(bankId);
    }


}

package com.abhinav.keepsafe.home.category.bank;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 20/10/17.
 */

public class EditBankPresenter extends BasePresenter<EditBankView> implements BaseModelListener {

    EditBankModel model;
    private LiveData<Bank> bankLiveData;
    private Observer<Bank> bankObserver;

    public EditBankPresenter(EditBankView view) {
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
        model = new EditBankModel(this);
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

    public void onSaveClicked(Bank bankObj) {
        model.dataManager.updateBank(bankObj);
        getView().popFragment();
    }

    public void onDeleteClicked(Bank bank) {
        model.dataManager.deleteBank(bank);
        getView().popFragment();
    }
}

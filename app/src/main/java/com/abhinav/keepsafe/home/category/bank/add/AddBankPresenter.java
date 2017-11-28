package com.abhinav.keepsafe.home.category.bank.add;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 12/10/17.
 */

public class AddBankPresenter extends BasePresenter<AddBankView> implements BaseModelListener {

    private AddBankModel model;

    public AddBankPresenter(AddBankView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new AddBankModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void addBank(Bank bank) {
        model.dataManager.addBank(bank)
                .subscribe(aLong -> {
                    getView().showToastShort("Bank Added Successfully");
                    getView().popFragment();
                });
    }
}

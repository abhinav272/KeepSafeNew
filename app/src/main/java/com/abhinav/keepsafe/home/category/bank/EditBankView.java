package com.abhinav.keepsafe.home.category.bank;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 20/10/17.
 */

interface EditBankView extends IBaseView {
    void showBankDetails(Bank bank);
}

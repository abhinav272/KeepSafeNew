package com.abhinav.keepsafe.home.category.bank;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Bank;

/**
 * Created by abhinav.sharma on 24/11/17.
 */

interface ShowBankView extends IBaseView{
    void showBankDetails(Bank bank);
    void showEditBankFragment(int bankId);
    void popFragment();
}

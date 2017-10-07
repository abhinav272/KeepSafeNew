package com.abhinav.keepsafe.data;

import android.arch.lifecycle.LiveData;

import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 23/04/17.
 */
public interface IDataManager {

    void savePin(String pin);
    String getSavedPin();

    /**
     * BankDAO methods
     * */
    LiveData<List<Bank>> fetchAllBanks();
    LiveData<Bank> fetchBankDetails(int bankId);
    void addBank(Bank bank);
    void updateBank(Bank bank);
    void deleteBank(Bank bank);

    /**
     * EmailDAO methods
     * */
    LiveData<List<Email>> fetchAllEmails();
    LiveData<Email> fetchEmailDetails(int emailId);
    void addEmail(Email email);
    void updateEmail(Email email);
    void deleteEmail(Email email);

}

package com.abhinav.keepsafe.data;

import android.arch.lifecycle.LiveData;

import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 23/04/17.
 */
public class DataManager implements IDataManager {

    private PreferenceHelper mPreferenceHelper;
    private DataBaseHelper mDataBaseHelper;
    private static volatile DataManager mDataManager;

    private DataManager() {
        if (mDataManager!=null)
            throw new IllegalStateException("Illegal state of DataManager");
        else {
            // initialize all data sources;
            mPreferenceHelper = new PreferenceHelper();
            mDataBaseHelper = DataBaseHelper.getInstance();
        }
    }

    public static DataManager getInstance() {
        if (mDataManager == null){
            synchronized (DataManager.class){
                if (mDataManager == null){
                    mDataManager = new DataManager();
                }
            }
        }
        return mDataManager;
    }


    @Override
    public void savePin(String pin) {
        mPreferenceHelper.saveUserPin(pin);
    }

    @Override
    public String getSavedPin() {
        return mPreferenceHelper.getUserPin();
    }

    @Override
    public LiveData<List<Bank>> fetchAllBanks() {
        return mDataBaseHelper.getBankDao().getAllBanks();
    }

    @Override
    public LiveData<Bank> fetchBankDetails(int bankId) {
        return mDataBaseHelper.getBankDao().getBankDetails(bankId);
    }

    @Override
    public void addBank(Bank bank) {
        mDataBaseHelper.getBankDao().addBank(bank);
    }

    @Override
    public void updateBank(Bank bank) {
        mDataBaseHelper.getBankDao().updateBank(bank);
    }

    @Override
    public void deleteBank(Bank bank) {
        mDataBaseHelper.getBankDao().deleteBank(bank);
    }

    @Override
    public LiveData<List<Email>> fetchAllEmails() {
        return mDataBaseHelper.getEmailDao().getAllEmails();
    }

    @Override
    public LiveData<Email> fetchEmailDetails(int emailId) {
        return mDataBaseHelper.getEmailDao().getEmailDetails(emailId);
    }

    @Override
    public void addEmail(Email email) {
        mDataBaseHelper.getEmailDao().addEmail(email);
    }

    @Override
    public void updateEmail(Email email) {
        mDataBaseHelper.getEmailDao().updateEmail(email);
    }

    @Override
    public void deleteEmail(Email email) {
        mDataBaseHelper.getEmailDao().deleteEmail(email);
    }
}

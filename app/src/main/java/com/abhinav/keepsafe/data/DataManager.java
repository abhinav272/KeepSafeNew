package com.abhinav.keepsafe.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Abhinav on 23/04/17.
 */
public class DataManager implements IDataManager {

    private PreferenceHelper mPreferenceHelper;
    private DataBaseHelper mDataBaseHelper;
    private static volatile DataManager mDataManager;

    private DataManager() {
        if (mDataManager != null)
            throw new IllegalStateException("Illegal state of DataManager");
        else {
            // initialize all data sources;
            mPreferenceHelper = new PreferenceHelper();
            mDataBaseHelper = DataBaseHelper.getInstance();
        }
    }

    public static DataManager getInstance() {
        if (mDataManager == null) {
            synchronized (DataManager.class) {
                if (mDataManager == null) {
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
    public Observable<Long> addBank(Bank bank) {
        return Observable.fromCallable(() -> mDataBaseHelper.getBankDao().addBank(bank))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateBank(Bank bank) {
        Completable.fromAction(() -> mDataBaseHelper.getBankDao().updateBank(bank))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void deleteBank(Bank bank) {
        Completable.fromAction(() -> mDataBaseHelper.getBankDao().deleteBank(bank))
                .subscribeOn(Schedulers.io())
                .subscribe();
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
    public Observable<Long> addEmail(Email email) {
        return Observable.fromCallable(() -> mDataBaseHelper.getEmailDao().addEmail(email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateEmail(Email email) {
        Completable.fromAction(() -> mDataBaseHelper.getEmailDao().updateEmail(email))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteEmail(Email email) {
        Completable.fromAction(() -> mDataBaseHelper.getEmailDao().deleteEmail(email))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public LiveData<List<SocialNetwork>> fetchAllSocialNetworkAccounts() {
        return mDataBaseHelper.getSocialNetworkDao().getAllSocialNetworkAccounts();
    }

    @Override
    public LiveData<SocialNetwork> fetchSocialNetworkDetails(int socialNetworkId) {
        return mDataBaseHelper.getSocialNetworkDao().getSocialNetworkDetails(socialNetworkId);
    }

    @Override
    public Observable<Long> addSocialNetwork(SocialNetwork socialNetwork) {
        return Observable.fromCallable(() -> mDataBaseHelper.getSocialNetworkDao().addSocialNetwork(socialNetwork))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateSocialNetwork(SocialNetwork socialNetwork) {
        Completable.fromAction(() -> mDataBaseHelper.getSocialNetworkDao().updateSocialNetwork(socialNetwork))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteSocialNetwork(SocialNetwork socialNetwork) {
        Completable.fromAction(() -> mDataBaseHelper.getSocialNetworkDao().deleteSocialNetwork(socialNetwork))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public LiveData<List<ECommerce>> fetchAllECommerceAccounts() {
        return mDataBaseHelper.getECommerceDao().getAllECommerceAccounts();
    }

    @Override
    public LiveData<ECommerce> fetchECommerceDetails(int eCommerceId) {
        return mDataBaseHelper.getECommerceDao().getECommerceDetails(eCommerceId);
    }

    @Override
    public Observable<Long> addECommerce(ECommerce eCommerce) {
        return Observable.fromCallable(() -> mDataBaseHelper.getECommerceDao().addECommerce(eCommerce))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateECommerce(ECommerce eCommerce) {
        Completable.fromAction(() -> mDataBaseHelper.getECommerceDao().updateECommerce(eCommerce))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteECommerce(ECommerce eCommerce) {
        Completable.fromAction(() -> mDataBaseHelper.getECommerceDao().deleteECommerce(eCommerce))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}

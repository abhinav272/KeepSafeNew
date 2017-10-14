package com.abhinav.keepsafe.data;

import android.arch.lifecycle.LiveData;

import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

import io.reactivex.Observable;

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
    Observable<Long> addBank(Bank bank);
    void updateBank(Bank bank);
    void deleteBank(Bank bank);

    /**
     * EmailDAO methods
     * */
    LiveData<List<Email>> fetchAllEmails();
    LiveData<Email> fetchEmailDetails(int emailId);
    Observable<Long> addEmail(Email email);
    void updateEmail(Email email);
    void deleteEmail(Email email);

    /**
     * SocialNetworkDAO methods
     * */
    LiveData<List<SocialNetwork>> fetchAllSocialNetworkAccounts();
    LiveData<SocialNetwork> fetchSocialNetworkDetails(int socialNetworkId);
    Observable<Long> addSocialNetwork(SocialNetwork socialNetwork);
    void updateSocialNetwork(SocialNetwork socialNetwork);
    void deleteSocialNetwork(SocialNetwork socialNetwork);

    /**
     * ECommerceDAO methods
     * */
    LiveData<List<ECommerce>> fetchAllECommerceAccounts();
    LiveData<ECommerce> fetchECommerceDetails(int eCommerceId);
    Observable<Long> addECommerce(ECommerce eCommerce);
    void updateECommerce(ECommerce eCommerce);
    void deleteECommerce(ECommerce eCommerce);




}

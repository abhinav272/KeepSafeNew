package com.abhinav.keepsafe.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> implements CategoryModelListener {

    private CategoryModel categoryModel;
    private final LiveData<List<SocialNetwork>> socialNetworkListLiveData;
    private final LiveData<List<Bank>> bankListLiveData;
    private final LiveData<List<Email>> emailListLiveData;
    private final LiveData<List<ECommerce>> eCommerceListLiveData;
    private Observer<List<Bank>> bankObserver;
    private Observer<List<Email>> emailObserver;
    private Observer<List<SocialNetwork>> socialNetworkObserver;
    private Observer<List<ECommerce>> eCommerceObserver;


    CategoryPresenter(CategoryView view) {
        super(view);
        bankObserver = banks -> {
            if (banks == null || banks.size() == 0)
                getView().showNoItemView();
            else
                getView().showBankListing(banks);
        };

        emailObserver = emails -> {
            if (emails == null || emails.size() == 0)
                getView().showNoItemView();
            else
                getView().showEmailListings(emails);
        };

        socialNetworkObserver = socialNetworks -> {
            if (socialNetworks == null || socialNetworks.size() == 0)
                getView().showNoItemView();
            else
                getView().showSocialNetworkListings(socialNetworks);
        };

        eCommerceObserver = eCommerces -> {
            if (eCommerces == null || eCommerces.size() == 0)
                getView().showNoItemView();
            else
                getView().showECommerceListings(eCommerces);
        };

        /**
         * created livedata objects separately to fix crash
         * */
        socialNetworkListLiveData = categoryModel.dataManager.fetchAllSocialNetworkAccounts();
        bankListLiveData = categoryModel.dataManager.fetchAllBanks();
        eCommerceListLiveData = categoryModel.dataManager.fetchAllECommerceAccounts();
        emailListLiveData = categoryModel.dataManager.fetchAllEmails();
    }

    @Override
    protected void setModel() {
        categoryModel = new CategoryModel(this);
    }

    @Override
    protected void destroy() {
        bankListLiveData.removeObserver(bankObserver);
        emailListLiveData.removeObserver(emailObserver);
        eCommerceListLiveData.removeObserver(eCommerceObserver);
        socialNetworkListLiveData.removeObserver(socialNetworkObserver);
        categoryModel.detachListener();
        categoryModel = null;
    }

    @Override
    protected void initView() {

    }

    public void fetchCategoryListing(int position) {
        switch (position) {
            case -1:
                getView().popFragmentOnInvalidChoice();
                break;
            case 0:
                bankListLiveData.observeForever(bankObserver);
                break;
            case 1:
                emailListLiveData.observeForever(emailObserver);
                break;
            case 2:
                socialNetworkListLiveData.observeForever(socialNetworkObserver);
                break;
            case 3:
                eCommerceListLiveData.observeForever(eCommerceObserver);
                break;
            case 4:
                break;
        }
    }

    public void fetchCategoryItemByPosition(int itemPosition, int selectedCategoryPosition) {
        switch (selectedCategoryPosition) {
            case -1:
                break;
            case 0:
                if (bankListLiveData != null && bankListLiveData.getValue() != null)
                    getView().showBankItem(bankListLiveData.getValue().get(itemPosition).getId());
                break;
            case 1:
                if (emailListLiveData != null && emailListLiveData.getValue() != null) {
                    getView().showEmailItem(emailListLiveData.getValue().get(itemPosition).getId());
                }
                break;
            case 2:
                if (socialNetworkListLiveData != null && socialNetworkListLiveData.getValue() != null) {
                    getView().showSocialNetworkItem(socialNetworkListLiveData.getValue().get(itemPosition).getId());
                }
                break;
            case 3:
                if (eCommerceListLiveData != null && eCommerceListLiveData.getValue() != null) {
                    getView().showECommerceItem(eCommerceListLiveData.getValue().get(itemPosition).getId());
                }
                break;
            case 4:
                break;
        }
    }
}

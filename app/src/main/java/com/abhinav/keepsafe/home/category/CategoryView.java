package com.abhinav.keepsafe.home.category;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public interface CategoryView extends IBaseView {
    void showToolbarTitle(int position);
    void popFragmentOnInvalidChoice();
    void showBankListing(List<Bank> value);
    void showNoItemView();
    void showEmailListings(List<Email> emails);
    void showSocialNetworkListings(List<SocialNetwork> socialNetworks);
    void showECommerceListings(List<ECommerce> eCommerces);
    void showBankItem(Bank bank);
    void showEmailItem(Email email);
    void showSocialNetworkItem(SocialNetwork socialNetwork);
    void showECommerceItem(ECommerce eCommerce);
    void addEditBankFragment(int bankId);
}

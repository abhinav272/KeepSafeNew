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
    void setupHeaderImage(int position);
    void showToolbarTitle(int position);
    void popFragmentOnInvalidChoice();
    void showBankListing(List<Bank> value);
    void showNoItemView();
    void showEmailListings(List<Email> emails);
    void showSocialNetworkListings(List<SocialNetwork> socialNetworks);
    void showECommerceListings(List<ECommerce> eCommerces);
    void showBankItem(int bankId);
    void showEmailItem(int emailId);
    void showSocialNetworkItem(int socialNetworkId);
    void showECommerceItem(int eCommerceId);
}

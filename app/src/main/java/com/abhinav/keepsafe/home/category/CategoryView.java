package com.abhinav.keepsafe.home.category;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Bank;

import java.util.List;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public interface CategoryView extends IBaseView {
    void showToolbarTitle(int position);
    void onCategoryListingClicked(int listingPosition);
    void popFragmentOnInvalidChoice();
    void showBankListing(List<Bank> value);
    void showNoItemView();
}

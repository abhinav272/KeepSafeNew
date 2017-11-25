package com.abhinav.keepsafe.home.category.ecommerce;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.ECommerce;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

interface ShowECommerceView extends IBaseView {

    void popFragment();
    void showECommerceDetails(ECommerce eCommerce);
    void showEditECommerceFragment(int ecommerceId);
}

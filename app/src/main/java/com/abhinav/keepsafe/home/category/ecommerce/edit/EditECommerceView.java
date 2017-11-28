package com.abhinav.keepsafe.home.category.ecommerce.edit;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.ECommerce;

/**
 * Created by abhinav.sharma on 22/10/17.
 */

interface EditECommerceView extends IBaseView {
    void popFragment();
    void showECommerceDetails(ECommerce eCommerce);
}

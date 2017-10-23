package com.abhinav.keepsafe.home.category.email;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Email;

/**
 * Created by abhinav.sharma on 21/10/17.
 */

interface EditEmailView extends IBaseView {
    void showEmailDetails(Email email);
    void popFragment();
}

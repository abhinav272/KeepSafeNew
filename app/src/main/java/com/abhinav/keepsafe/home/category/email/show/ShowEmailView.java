package com.abhinav.keepsafe.home.category.email.show;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Email;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

interface ShowEmailView extends IBaseView {

    void showEmailDetails(Email email);
    void showEditEmailFragment(int emailId);
    void popFragment();
}

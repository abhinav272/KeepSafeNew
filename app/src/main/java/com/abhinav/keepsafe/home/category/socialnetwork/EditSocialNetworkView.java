package com.abhinav.keepsafe.home.category.socialnetwork;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by abhinav.sharma on 22/10/17.
 */

interface EditSocialNetworkView extends IBaseView {
    void popFragment();

    void showSocialNetworkDetails(SocialNetwork socialNetwork);
}

package com.abhinav.keepsafe.home.category.socialnetwork;

import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

interface ShowSocialNetworkView extends IBaseView {

    void popFragment();
    void showSocialNetworkDetails(SocialNetwork socialNetwork);
    void showEditSocialNetworkFragment(int socialNetworkId);
}

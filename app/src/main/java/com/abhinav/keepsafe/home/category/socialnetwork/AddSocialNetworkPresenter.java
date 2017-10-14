package com.abhinav.keepsafe.home.category.socialnetwork;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by abhinav.sharma on 14/10/17.
 */

public class AddSocialNetworkPresenter extends BasePresenter<AddSocialNetworkView> implements BaseModelListener {

    private AddSocialNetworkModel model;

    public AddSocialNetworkPresenter(AddSocialNetworkView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new AddSocialNetworkModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void addSocialNetwork(SocialNetwork socialNetwork) {
        model.dataManager.addSocialNetwork(socialNetwork).subscribe(aLong -> {
            getView().showToastLong("Social Network Account Added Successfully");
            getView().popFragment();
        });
    }
}

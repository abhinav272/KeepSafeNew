package com.abhinav.keepsafe.home.category.socialnetwork.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by abhinav.sharma on 22/10/17.
 */

class EditSocialNetworkPresenter extends BasePresenter<EditSocialNetworkView> implements BaseModelListener {

    private EditSocialNetworkModel model;
    private LiveData<SocialNetwork> socialNetworkLiveData;
    private Observer<SocialNetwork> socialNetworkObserver;

    public EditSocialNetworkPresenter(EditSocialNetworkView view) {
        super(view);
        socialNetworkObserver = socialNetwork -> {
            if (socialNetwork != null) {
                getView().showSocialNetworkDetails(socialNetwork);
            } else {
                getView().popFragment();
            }
        };
    }

    @Override
    protected void setModel() {
        model = new EditSocialNetworkModel(this);
    }

    @Override
    protected void destroy() {
        socialNetworkLiveData.removeObserver(socialNetworkObserver);
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    protected void fetchSocialNetworkDetails(int socialNetworkId) {
        socialNetworkLiveData = model.dataManager.fetchSocialNetworkDetails(socialNetworkId);
        socialNetworkLiveData.observeForever(socialNetworkObserver);
    }

    protected void onSaveClicked(SocialNetwork socialNetwork) {
        model.dataManager.updateSocialNetwork(socialNetwork);
        getView().popFragment();
    }

    protected void onDeleteClicked(SocialNetwork socialNetwork) {
        model.dataManager.deleteSocialNetwork(socialNetwork);
        getView().popFragment();
    }
}

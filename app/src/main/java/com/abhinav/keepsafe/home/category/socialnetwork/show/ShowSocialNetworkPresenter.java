package com.abhinav.keepsafe.home.category.socialnetwork.show;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import com.abhinav.keepsafe.base.BaseModelListener;
import com.abhinav.keepsafe.base.BasePresenter;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

public class ShowSocialNetworkPresenter extends BasePresenter<ShowSocialNetworkView> implements BaseModelListener {

    private ShowSocialNetworkModel model;
    private Observer<SocialNetwork> socialNetworkObserver;
    private LiveData<SocialNetwork> socialNetworkLiveData;

    public ShowSocialNetworkPresenter(ShowSocialNetworkView view) {
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
        model = new ShowSocialNetworkModel(this);
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


    void fetchSocialNetworkDetails(int socialNetworkId) {
        socialNetworkLiveData = model.dataManager.fetchSocialNetworkDetails(socialNetworkId);
        socialNetworkLiveData.observeForever(socialNetworkObserver);
    }

    void onEditClicked(int socialNetworkId) {
        getView().showEditSocialNetworkFragment(socialNetworkId);
    }
}

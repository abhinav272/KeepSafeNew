package com.abhinav.keepsafe.home.category.socialnetwork.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.SocialNetwork;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_IMAGE;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class AddSocialNetworkFragment extends BaseFragment implements AddSocialNetworkView, AppBarLayout.OnOffsetChangedListener {


    Unbinder unbinder;
    AddSocialNetworkPresenter presenter;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.vs_add_category_item)
    ViewStub vsAddCategoryItem;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    private TextInputLayout til_social_network_platform_name, til_email_id_used,
            til_social_network_password, til_social_network_user_name;
    private EditText et_social_network_platform_name, et_email_id_used, et_social_network_password,
            et_social_network_user_name;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new AddSocialNetworkPresenter(this);
        initCoordinatorView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateSocialNetworkViewStub();
        initSocialNetworkStubView(view);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivHeader.setImageResource(R.drawable.img_social_header);
        collapsing.setTitle("Add Social Network Details");
    }

    private void inflateSocialNetworkViewStub() {
        vsAddCategoryItem.setInflatedId(R.id.layout_social_network_details);
        vsAddCategoryItem.setLayoutResource(R.layout.fragment_add_social_network);
        vsAddCategoryItem.inflate();
    }

    private void initSocialNetworkStubView(View view) {
        til_social_network_password = view.findViewById(R.id.til_social_network_password);
        til_social_network_platform_name = view.findViewById(R.id.til_social_network_platform_name);
        til_social_network_user_name = view.findViewById(R.id.til_social_network_user_name);
        til_email_id_used = view.findViewById(R.id.til_email_id_used);

        et_social_network_password = view.findViewById(R.id.et_social_network_password);
        et_social_network_platform_name = view.findViewById(R.id.et_social_network_platform_name);
        et_social_network_user_name = view.findViewById(R.id.et_social_network_username);
        et_email_id_used = view.findViewById(R.id.et_email_id_used);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        if (fireValidations()) {
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setPlatformName(et_social_network_platform_name.getText().toString().trim());
            socialNetwork.setEmailIdUsed(et_email_id_used.getText().toString().trim());
            socialNetwork.setUsername(et_social_network_user_name.getText().toString().trim());
            socialNetwork.setPassword(et_social_network_password.getText().toString().trim());
            presenter.addSocialNetwork(socialNetwork);
        }
    }

    private boolean fireValidations() {
        boolean flag = true;
        if (TextUtils.isEmpty(et_social_network_platform_name.getText().toString().trim())) {
            til_social_network_platform_name.setError(getString(R.string.invalid_social_platform));
            flag = false;
        }

        if (TextUtils.isEmpty(et_email_id_used.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(et_email_id_used.getText().toString().trim())) {
            til_email_id_used.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(et_social_network_password.getText().toString().trim())) {
            til_social_network_password.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        return flag;
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ViewCompat.animate(fab).scaleY(0).scaleX(0).start();

            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(fab).scaleY(1).scaleX(1).start();
            }
        }
    }
}

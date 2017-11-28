package com.abhinav.keepsafe.home.category.ecommerce.add;

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
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_IMAGE;

/**
 * Created by abhinav.sharma on 14/10/17.
 */

public class AddECommerceFragment extends BaseFragment implements AddECommerceView, AppBarLayout.OnOffsetChangedListener {

    Unbinder unbinder;
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
    private TextInputLayout til_ecommerce_platform_name, til_ecommerce_password, til_email_id_used,
            til_ecommerce_user_name;
    private EditText et_ecommerce_user_name, et_email_id_used, et_ecommerce_password, et_ecommerce_platform_name;
    private AddECommercePresenter presenter;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new AddECommercePresenter(this);
        initCoordinatorView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateECommerceViewStub();
        initECommerceStubView(view);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivHeader.setImageResource(R.drawable.img_ecommerce_header);
        collapsing.setTitle("Add E-Commerce Details");
    }

    private void inflateECommerceViewStub() {
        vsAddCategoryItem.setInflatedId(R.id.layout_ecommerce_details);
        vsAddCategoryItem.setLayoutResource(R.layout.fragment_add_ecommerce);
        vsAddCategoryItem.inflate();
    }

    private void initECommerceStubView(View view) {
        til_ecommerce_password = view.findViewById(R.id.til_ecommerce_password);
        til_ecommerce_platform_name = view.findViewById(R.id.til_ecommerce_platform_name);
        til_ecommerce_user_name = view.findViewById(R.id.til_ecommerce_user_name);
        til_email_id_used = view.findViewById(R.id.til_email_id_used);

        et_ecommerce_password = view.findViewById(R.id.et_ecommerce_password);
        et_ecommerce_platform_name = view.findViewById(R.id.et_ecommerce_platform_name);
        et_ecommerce_user_name = view.findViewById(R.id.et_ecommerce_user_name);
        et_email_id_used = view.findViewById(R.id.et_email_id_used);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    private boolean fireValidations() {
        resetAllErrors();
        boolean flag = true;
        if (TextUtils.isEmpty(et_ecommerce_platform_name.getText().toString().trim())) {
            til_ecommerce_platform_name.setError(getString(R.string.invalid_social_platform));
            flag = false;
        }

        if (TextUtils.isEmpty(et_email_id_used.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(et_email_id_used.getText().toString().trim())) {
            til_email_id_used.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(et_ecommerce_password.getText().toString().trim())) {
            til_ecommerce_password.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        return flag;
    }

    private void resetAllErrors() {
        til_ecommerce_password.setError(null);
        til_email_id_used.setError(null);
        til_ecommerce_platform_name.setError(null);
        til_ecommerce_user_name.setError(null);
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        if (fireValidations()) {
            ECommerce eCommerce = new ECommerce();
            eCommerce.setPlatformName(et_ecommerce_platform_name.getText().toString().trim());
            eCommerce.setEmailIdUsed(et_email_id_used.getText().toString().trim());
            eCommerce.setPassword(et_ecommerce_password.getText().toString().trim());
            eCommerce.setUsername(et_ecommerce_user_name.getText().toString().trim());
            presenter.addECommerce(eCommerce);
        }
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

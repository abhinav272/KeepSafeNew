package com.abhinav.keepsafe.home.category.email;

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
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_IMAGE;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class AddEmailFragment extends BaseFragment implements AddEmailView, AppBarLayout.OnOffsetChangedListener {


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
    private AddEmailPresenter presenter;
    private TextInputLayout til_email_id, til_platform_name, til_email_password, til_recovery_email;
    private EditText et_email_id, et_platform_name, et_email_password, et_recovery_email;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        presenter = new AddEmailPresenter(this);
        View view = inflater.inflate(R.layout.fragment_add_category_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCoordinatorView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateECommerceViewStub();
        initECommerceStubView(view);
    }

    private void inflateECommerceViewStub() {
        vsAddCategoryItem.setInflatedId(R.id.layout_email_details);
        vsAddCategoryItem.setLayoutResource(R.layout.fragment_add_email);
        vsAddCategoryItem.inflate();
    }

    private void initECommerceStubView(View view) {
        til_email_password = view.findViewById(R.id.til_email_password);
        til_platform_name = view.findViewById(R.id.til_email_platform);
        til_email_id = view.findViewById(R.id.til_email_id);
        til_recovery_email = view.findViewById(R.id.til_recovery_email);

        et_email_id = view.findViewById(R.id.et_email_id);
        et_email_password = view.findViewById(R.id.et_email_password);
        et_platform_name = view.findViewById(R.id.et_email_platform);
        et_recovery_email = view.findViewById(R.id.et_recovery_email);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivHeader.setImageResource(R.drawable.img_email_header);
        collapsing.setTitle("Add Email Details");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        onSaveClicked();
    }

    private boolean fireValidations() {
        resetAllErrors();
        boolean flag = true;
        if (TextUtils.isEmpty(et_email_id.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(et_email_id.getText().toString().trim())) {
            til_email_id.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(et_email_password.getText().toString().trim())) {
            til_email_password.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        if (TextUtils.isEmpty(et_recovery_email.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(et_recovery_email.getText().toString().trim())) {
            til_recovery_email.setError(getString(R.string.invalid_recovery_email));
            flag = false;
        }

        if (TextUtils.isEmpty(et_platform_name.getText().toString().trim())) {
            til_platform_name.setError(getString(R.string.invalid_platform));
            flag = false;
        }
        return flag;
    }

    private void resetAllErrors() {
        til_email_id.setError(null);
        til_email_password.setError(null);
        til_platform_name.setError(null);
        til_recovery_email.setError(null);
    }

    @Override
    public void onSaveClicked() {
        if (fireValidations()) {
            Email email = new Email();
            email.setEmailId(et_email_id.getText().toString().trim());
            email.setEmailPassword(et_email_password.getText().toString().trim());
            email.setRecoveryEmail(et_recovery_email.getText().toString().trim());
            email.setPlatformName(et_platform_name.getText().toString().trim());
            presenter.addEmail(email);
        }
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

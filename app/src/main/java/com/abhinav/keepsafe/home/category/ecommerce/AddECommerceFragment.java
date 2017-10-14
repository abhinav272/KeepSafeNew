package com.abhinav.keepsafe.home.category.ecommerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 14/10/17.
 */

public class AddECommerceFragment extends BaseFragment implements AddECommerceView {
    @BindView(R.id.tv_ecommerce_logo)
    TextView tvEcommerceLogo;
    @BindView(R.id.tv_ecommerce_platform_name)
    EditText tvEcommercePlatformName;
    @BindView(R.id.tv_ecommerce_password)
    EditText tvEcommercePassword;
    @BindView(R.id.tv_email_id_used)
    EditText tvEmailIdUsed;
    @BindView(R.id.tv_ecommerce_user_name)
    EditText tvEcommerceUserName;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;
    private AddECommercePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_ecommerce, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new AddECommercePresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        if (fireValidations()) {
            ECommerce eCommerce = new ECommerce();
            eCommerce.setPlatformName(tvEcommercePlatformName.getText().toString().trim());
            eCommerce.setEmailIdUsed(tvEmailIdUsed.getText().toString().trim());
            eCommerce.setPassword(tvEcommercePassword.getText().toString().trim());
            eCommerce.setUsername(tvEcommerceUserName.getText().toString().trim());
            presenter.addECommerce(eCommerce);
        }
    }

    private boolean fireValidations() {
        boolean flag = true;
        if (TextUtils.isEmpty(tvEcommercePlatformName.getText().toString().trim())) {
            tvEcommercePlatformName.setError(getString(R.string.invalid_social_platform));
            flag = false;
        }

        if (TextUtils.isEmpty(tvEmailIdUsed.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(tvEmailIdUsed.getText().toString().trim())) {
            tvEmailIdUsed.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(tvEcommercePassword.getText().toString().trim())) {
            tvEcommercePassword.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        return flag;
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }
}

package com.abhinav.keepsafe.home.category.socialnetwork;

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
import com.abhinav.keepsafe.entities.SocialNetwork;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class AddSocialNetworkFragment extends BaseFragment implements AddSocialNetworkView {

    @BindView(R.id.tv_social_network_logo)
    TextView tvSocialNetworkLogo;
    @BindView(R.id.tv_social_network_platform_name)
    EditText tvSocialNetworkPlatformName;
    @BindView(R.id.tv_social_network_password)
    EditText tvSocialNetworkPassword;
    @BindView(R.id.tv_email_id_used)
    EditText tvEmailIdUsed;
    @BindView(R.id.tv_social_network_user_name)
    EditText tvSocialNetworkUserName;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;
    AddSocialNetworkPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_social_network, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new AddSocialNetworkPresenter(this);
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
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setPlatformName(tvSocialNetworkPlatformName.getText().toString().trim());
            socialNetwork.setEmailIdUsed(tvEmailIdUsed.getText().toString().trim());
            socialNetwork.setUsername(tvSocialNetworkUserName.getText().toString().trim());
            socialNetwork.setPassword(tvSocialNetworkPassword.getText().toString().trim());
            presenter.addSocialNetwork(socialNetwork);
        }
    }

    private boolean fireValidations() {
        boolean flag = true;
        if (TextUtils.isEmpty(tvSocialNetworkPlatformName.getText().toString().trim())) {
            tvSocialNetworkPlatformName.setError(getString(R.string.invalid_social_platform));
            flag = false;
        }

        if (TextUtils.isEmpty(tvEmailIdUsed.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(tvEmailIdUsed.getText().toString().trim())) {
            tvEmailIdUsed.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(tvSocialNetworkPassword.getText().toString().trim())) {
            tvSocialNetworkPassword.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        return flag;
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }
}

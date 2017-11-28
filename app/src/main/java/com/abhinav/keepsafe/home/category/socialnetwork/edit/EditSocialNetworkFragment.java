package com.abhinav.keepsafe.home.category.socialnetwork.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.SocialNetwork;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 22/10/17.
 */

public class EditSocialNetworkFragment extends BaseFragment implements EditSocialNetworkView {

    @BindView(R.id.tv_platform_name)
    TextView tvPlatformName;
    @BindView(R.id.et_email_id_used)
    EditText etEmailIdUsed;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_social_network_username)
    EditText etSocialNetworkUsername;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;
    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;
    Unbinder unbinder;
    private SocialNetwork socialNetwork;
    private int socialNetworkId;
    private EditSocialNetworkPresenter presenter;


    public static EditSocialNetworkFragment getInstance(int socialNetworkId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.SOCIAL_NETWORK_ID, socialNetworkId);
        EditSocialNetworkFragment editSocialNetworkFragment = new EditSocialNetworkFragment();
        editSocialNetworkFragment.setArguments(bundle);
        return editSocialNetworkFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_social_network, container, false);
        socialNetworkId = getArguments().getInt(Constants.ExtrasKey.SOCIAL_NETWORK_ID, -1);
        unbinder = ButterKnife.bind(this, view);
        presenter = new EditSocialNetworkPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.fetchSocialNetworkDetails(socialNetworkId);
        setupEditMode();
    }

    private void setupEditMode() {
        etEmailIdUsed.setEnabled(true);
        etPassword.setEnabled(true);
        etSocialNetworkUsername.setEnabled(true);
    }

    @Override
    public void showSocialNetworkDetails(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        tvPlatformName.setText(socialNetwork.getPlatformName());
        etSocialNetworkUsername.setText(socialNetwork.getUsername());
        etEmailIdUsed.setText(socialNetwork.getEmailIdUsed());
        etPassword.setText(socialNetwork.getPassword());
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick({R.id.fab_delete, R.id.fab_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_delete:
                presenter.onDeleteClicked(socialNetwork);
                break;
            case R.id.fab_save:
                presenter.onSaveClicked(getSocialNetworkObj());
                break;
        }
    }

    private SocialNetwork getSocialNetworkObj() {
        socialNetwork.setEmailIdUsed(etEmailIdUsed.getText().toString().trim());
        socialNetwork.setUsername(etSocialNetworkUsername.getText().toString().trim());
        socialNetwork.setPassword(etPassword.getText().toString().trim());
        return socialNetwork;
    }
}

package com.abhinav.keepsafe.home.category.email.edit;

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
import com.abhinav.keepsafe.entities.Email;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 21/10/17.
 */

public class EditEmailFragment extends BaseFragment implements EditEmailView {

    @BindView(R.id.tv_email_id)
    TextView tvEmailId;
    @BindView(R.id.et_platform_name)
    EditText etPlatformName;
    @BindView(R.id.et_email_password)
    EditText etEmailPassword;
    @BindView(R.id.et_recovery_email)
    EditText etRecoveryEmail;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;
    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;
    Unbinder unbinder;
    private int emailId;
    private Email email;
    private EditEmailPresenter presenter;

    public static EditEmailFragment getInstance(int emailId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.EMAIL_ID, emailId);
        EditEmailFragment editEmailFragment = new EditEmailFragment();
        editEmailFragment.setArguments(bundle);
        return editEmailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_email, container, false);
        emailId = getArguments().getInt(Constants.ExtrasKey.EMAIL_ID, -1);
        unbinder = ButterKnife.bind(this, view);
        presenter = new EditEmailPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.fetchEmailDetails(emailId);
        setupEditMode();
    }

    private void setupEditMode() {
        etEmailPassword.setEnabled(true);
        etPlatformName.setEnabled(true);
        etRecoveryEmail.setEnabled(true);
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
                presenter.onDeleteClicked(email);
                break;
            case R.id.fab_save:
                presenter.onSaveClicked(getEmailObj());
                break;
        }
    }

    private Email getEmailObj() {
        email.setPlatformName(etPlatformName.getText().toString().trim());
        email.setEmailPassword(etEmailPassword.getText().toString().trim());
        email.setRecoveryEmail(etRecoveryEmail.getText().toString().trim());
        return email;
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showEmailDetails(Email email) {
        this.email = email;
        tvEmailId.setText(email.getEmailId());
        etRecoveryEmail.setText(email.getRecoveryEmail());
        etPlatformName.setText(email.getPlatformName());
        etEmailPassword.setText(email.getEmailPassword());
    }
}

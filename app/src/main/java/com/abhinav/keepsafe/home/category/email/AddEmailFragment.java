package com.abhinav.keepsafe.home.category.email;

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
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.utils.KeepSafeRegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class AddEmailFragment extends BaseFragment implements AddEmailView {

    @BindView(R.id.tv_email_logo)
    TextView tvEmailLogo;
    @BindView(R.id.tv_email_id)
    EditText tvEmailId;
    @BindView(R.id.tv_email_password)
    EditText tvEmailPassword;
    @BindView(R.id.tv_recovery_email)
    EditText tvRecoveryEmail;
    @BindView(R.id.tv_platform_name)
    EditText tvPlatformName;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;
    private AddEmailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        presenter = new AddEmailPresenter(this);
        View view = inflater.inflate(R.layout.fragment_add_email, container, false);
        unbinder = ButterKnife.bind(this, view);
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
        onSaveClicked();
    }

    private boolean fireValidations() {
        boolean flag = true;
        if (TextUtils.isEmpty(tvEmailId.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(tvEmailId.getText().toString().trim())) {
            tvEmailId.setError(getString(R.string.invalid_email));
            flag = false;
        }

        if (TextUtils.isEmpty(tvEmailPassword.getText().toString().trim())) {
            tvEmailPassword.setError(getString(R.string.invalid_email_password));
            flag = false;
        }

        if (TextUtils.isEmpty(tvRecoveryEmail.getText().toString().trim()) ||
                !KeepSafeRegexUtil.isValidEmail(tvRecoveryEmail.getText().toString().trim())) {
            tvRecoveryEmail.setError(getString(R.string.invalid_recovery_email));
            flag = false;
        }

        if (TextUtils.isEmpty(tvPlatformName.getText().toString().trim())) {
            tvPlatformName.setError(getString(R.string.invalid_platform));
            flag = false;
        }
        return flag;
    }

    @Override
    public void onSaveClicked() {
        if (fireValidations()){
            Email email = new Email();
            email.setEmailId(tvEmailId.getText().toString().trim());
            email.setEmailPassword(tvEmailPassword.getText().toString().trim());
            email.setRecoveryEmail(tvRecoveryEmail.getText().toString().trim());
            email.setPlatformName(tvPlatformName.getText().toString().trim());
            presenter.addEmail(email);
        }
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }
}

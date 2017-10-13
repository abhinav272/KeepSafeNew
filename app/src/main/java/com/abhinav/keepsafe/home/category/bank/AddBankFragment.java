package com.abhinav.keepsafe.home.category.bank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.Bank;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 12/10/17.
 */

public class AddBankFragment extends BaseFragment implements AddBankView {

    @BindView(R.id.tv_bank_logo)
    TextView tvBankLogo;
    @BindView(R.id.tv_bank_name)
    EditText tvBankName;
    @BindView(R.id.tv_account_type)
    Spinner tvAccountType;
    @BindView(R.id.tv_account_number)
    EditText tvAccountNumber;
    @BindView(R.id.tv_debit_card_number)
    EditText tvDebitCardNumber;
    @BindView(R.id.tv_credit_card_number)
    EditText tvCreditCardNumber;
    @BindView(R.id.tv_debit_card_pin)
    EditText tvDebitCardPin;
    @BindView(R.id.tv_credit_card_pin)
    EditText tvCreditCardPin;
    @BindView(R.id.tv_net_banking_id)
    EditText tvNetBankingId;
    @BindView(R.id.tv_net_banking_password)
    EditText tvNetBankingPassword;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;
    private AddBankPresenter presenter;
    private Bank bank;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        presenter = new AddBankPresenter(this);
        View view = inflater.inflate(R.layout.fragment_add_bank, container, false);
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

    @Override
    public void onSaveClicked() {
        if (fireValidations()) {
            bank = new Bank();
            bank.setBankName(tvBankName.getText().toString().trim());
            bank.setAccountType(tvAccountType.getSelectedItem().toString().trim());
            bank.setAccountNumber(tvAccountNumber.getText().toString().trim());
            bank.setCreditCardNumber(tvCreditCardNumber.getText().toString().trim());
            bank.setDebitCardNumber(tvDebitCardNumber.getText().toString().trim());
            bank.setCreditCardPin(tvCreditCardPin.getText().toString().trim());
            bank.setDebitCardPin(tvDebitCardPin.getText().toString().trim());
            bank.setCustomerId(tvNetBankingId.getText().toString().trim());
            bank.setNetBankingPassword(tvNetBankingPassword.getText().toString().trim());
            presenter.addBank(bank);
        }
    }

    private boolean fireValidations() {

        return true;
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }
}

package com.abhinav.keepsafe.home.category.bank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.Bank;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 18/10/17.
 */

public class EditBankFragment extends BaseFragment implements EditBankView {


    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.et_account_number)
    EditText etAccountNumber;
    @BindView(R.id.et_debit_card_number)
    EditText etDebitCardNumber;
    @BindView(R.id.et_credit_card_number)
    EditText etCreditCardNumber;
    @BindView(R.id.et_debit_card_pin)
    EditText etDebitCardPin;
    @BindView(R.id.et_credit_card_pin)
    EditText etCreditCardPin;
    @BindView(R.id.et_net_banking_id)
    EditText etNetBankingId;
    @BindView(R.id.et_net_banking_password)
    EditText etNetBankingPassword;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;
    private int bankId;
    private EditBankPresenter presenter;

    public static EditBankFragment getInstance(int bankId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.BANK_ID, bankId);
        EditBankFragment editBankFragment = new EditBankFragment();
        editBankFragment.setArguments(bundle);
        return editBankFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_bank, container, false);
        bankId = getArguments().getInt(Constants.ExtrasKey.BANK_ID, -1);
        presenter = new EditBankPresenter(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.fetchBankDetails(bankId);
        setupEditMode();
    }

    private void setupEditMode() {
        etAccountNumber.setEnabled(true);
        etCreditCardNumber.setEnabled(true);
        etDebitCardNumber.setEnabled(true);
        etCreditCardPin.setEnabled(true);
        etDebitCardPin.setEnabled(true);
        etNetBankingId.setEnabled(true);
        etNetBankingPassword.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {

    }

    @Override
    public void showBankDetails(Bank bank) {
        tvBankName.setText(bank.getBankName());
        etAccountNumber.setText(bank.getAccountNumber());
        etCreditCardNumber.setText(bank.getCreditCardNumber());
        etDebitCardNumber.setText(bank.getDebitCardNumber());
        etCreditCardPin.setText(bank.getCreditCardPin());
        etDebitCardPin.setText(bank.getDebitCardPin());
        etNetBankingId.setText(bank.getCustomerId());
        etNetBankingPassword.setText(bank.getNetBankingPassword());
    }
}

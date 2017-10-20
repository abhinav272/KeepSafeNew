package com.abhinav.keepsafe.home.category.bank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 18/10/17.
 */

public class EditBankFragment extends BaseFragment {


    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_account_number_placeholder)
    TextView tvAccountNumberPlaceholder;
    @BindView(R.id.et_account_number)
    EditText etAccountNumber;
    @BindView(R.id.tv_debit_card_number_placeholder)
    TextView tvDebitCardNumberPlaceholder;
    @BindView(R.id.et_debit_card_number)
    EditText etDebitCardNumber;
    @BindView(R.id.tv_credit_card_number_placeholder)
    TextView tvCreditCardNumberPlaceholder;
    @BindView(R.id.et_credit_card_number)
    EditText etCreditCardNumber;
    @BindView(R.id.tv_debit_card_pin_placeholder)
    TextView tvDebitCardPinPlaceholder;
    @BindView(R.id.et_debit_card_pin)
    EditText etDebitCardPin;
    @BindView(R.id.tv_credit_card_pin_placeholder)
    TextView tvCreditCardPinPlaceholder;
    @BindView(R.id.et_credit_card_pin)
    EditText etCreditCardPin;
    @BindView(R.id.tv_net_banking_id_placeholder)
    TextView tvNetBankingIdPlaceholder;
    @BindView(R.id.et_net_banking_id)
    EditText etNetBankingId;
    @BindView(R.id.tv_net_banking_password_placeholder)
    TextView tvNetBankingPasswordPlaceholder;
    @BindView(R.id.et_net_banking_password)
    EditText etNetBankingPassword;
    @BindView(R.id.tv_save)
    TextView tvSave;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_bank, container, false);
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
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
    }
}

package com.abhinav.keepsafe.home.category.bank;

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
import android.widget.Spinner;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.entities.Bank;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_IMAGE;

/**
 * Created by abhinav.sharma on 12/10/17.
 */

public class AddBankFragment extends BaseFragment implements AddBankView, AppBarLayout.OnOffsetChangedListener {


    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.vs_add_category_item)
    ViewStub vsCategoryItem;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    private EditText etBankName, etAccountNumber, etDebitCardNumber, etCreditCardNumber, etDebitCardPin,
            etCreditCardPin, etNetBankingUserId, etNetBankingPassword;
    private TextInputLayout tilBankName, tilAccountNumber, tilDebitCardNumber, tilCreditCardNumber, tilDebitCardPin,
            tilCreditCardPin, tilNetBankingId, tilNetBankingPassword;
    private Spinner tvAccountType;
    private AddBankPresenter presenter;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        presenter = new AddBankPresenter(this);
        View view = inflater.inflate(R.layout.fragment_add_category_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        initCoordinatorView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateBankViewStub();
        initBankStubView(view);
    }

    private void inflateBankViewStub() {
        vsCategoryItem.setInflatedId(R.id.layout_bank_details);
        vsCategoryItem.setLayoutResource(R.layout.fragment_add_bank);
        vsCategoryItem.inflate();
    }

    private void initBankStubView(View view) {
        tilBankName = view.findViewById(R.id.til_bank_name);
        tilDebitCardNumber = view.findViewById(R.id.til_debit_card_number);
        tilDebitCardPin = view.findViewById(R.id.til_debit_card_pin);
        tilCreditCardNumber = view.findViewById(R.id.til_credit_card_number);
        tilCreditCardPin = view.findViewById(R.id.til_credit_card_pin);
        tilNetBankingPassword = view.findViewById(R.id.til_netbanking_password);
        tilNetBankingId = view.findViewById(R.id.til_netbanking_id);
        tilAccountNumber = view.findViewById(R.id.til_account_number);

        etBankName = view.findViewById(R.id.tv_bank_name);
        etAccountNumber = view.findViewById(R.id.tv_account_number);
        etDebitCardNumber = view.findViewById(R.id.tv_debit_card_number);
        etCreditCardNumber = view.findViewById(R.id.tv_credit_card_number);
        etDebitCardPin = view.findViewById(R.id.tv_debit_card_pin);
        etCreditCardPin = view.findViewById(R.id.tv_credit_card_pin);
        etNetBankingUserId = view.findViewById(R.id.tv_net_banking_id);
        etNetBankingPassword = view.findViewById(R.id.tv_net_banking_password);
        tvAccountType = view.findViewById(R.id.tv_account_type);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivHeader.setImageResource(R.drawable.img_bank_header);
        collapsing.setTitle("Add Bank Details");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @Override
    public void onSaveClicked() {
        if (fireValidations()) {
            Bank bank = new Bank();
            bank.setBankName(etBankName.getText().toString().trim().toUpperCase());
            bank.setAccountType(tvAccountType.getSelectedItem().toString().trim());
            bank.setAccountNumber(etAccountNumber.getText().toString().trim());
            bank.setCreditCardNumber(etCreditCardNumber.getText().toString().trim());
            bank.setDebitCardNumber(etDebitCardNumber.getText().toString().trim());
            bank.setCreditCardPin(etCreditCardPin.getText().toString().trim());
            bank.setDebitCardPin(etDebitCardPin.getText().toString().trim());
            bank.setCustomerId(etNetBankingUserId.getText().toString().trim());
            bank.setNetBankingPassword(etNetBankingPassword.getText().toString().trim());
            presenter.addBank(bank);
        }
    }

    private boolean fireValidations() {
        resetAllErrors();
        boolean flag = true;
        if (TextUtils.isEmpty(etBankName.getText().toString().trim())) {
            tilBankName.setError(getString(R.string.invalid_bank_name));
            flag = false;
        }

        if (TextUtils.isEmpty(etAccountNumber.getText().toString().trim())) {
            tilAccountNumber.setError(getString(R.string.invalid_account_number));
            flag = false;
        }

        if (TextUtils.isEmpty(etCreditCardNumber.getText().toString().trim())) {
            tilCreditCardNumber.setError(getString(R.string.invalid_cc_number));
            flag = false;
        }

        if (TextUtils.isEmpty(etDebitCardNumber.getText().toString().trim())) {
            tilDebitCardNumber.setError(getString(R.string.invalid_debit_card_number));
            flag = false;
        }

        if (TextUtils.isEmpty(etCreditCardPin.getText().toString().trim())) {
            tilCreditCardPin.setError(getString(R.string.invalid_cc_pin));
            flag = false;
        }

        if (TextUtils.isEmpty(etDebitCardPin.getText().toString().trim())) {
            tilDebitCardPin.setError(getString(R.string.invalid_debit_card_pin));
            flag = false;
        }

        if (TextUtils.isEmpty(etNetBankingUserId.getText().toString().trim())) {
            tilNetBankingId.setError(getString(R.string.invalid_net_banking_id));
            flag = false;
        }

        if (TextUtils.isEmpty(etNetBankingPassword.getText().toString().trim())) {
            tilNetBankingPassword.setError(getString(R.string.invalid_net_banking_password));
            flag = false;
        }
        return flag;
    }

    private void resetAllErrors() {
        tilBankName.setError(null);
        tilAccountNumber.setError(null);
        tilDebitCardPin.setError(null);
        tilDebitCardNumber.setError(null);
        tilCreditCardNumber.setError(null);
        tilCreditCardPin.setError(null);
        tilNetBankingId.setError(null);
        tilNetBankingPassword.setError(null);
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

    @OnClick(R.id.fab)
    public void onViewClicked() {
        onSaveClicked();
    }
}

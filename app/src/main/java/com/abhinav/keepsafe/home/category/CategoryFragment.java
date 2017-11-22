package com.abhinav.keepsafe.home.category;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.AdapterItemClickListener;
import com.abhinav.keepsafe.adapter.BankAdapter;
import com.abhinav.keepsafe.adapter.ECommerceAdapter;
import com.abhinav.keepsafe.adapter.EmailAdapter;
import com.abhinav.keepsafe.adapter.SocialNetworkAdapter;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;
import com.abhinav.keepsafe.home.category.bank.EditBankFragment;
import com.abhinav.keepsafe.home.category.ecommerce.EditECommerceFragment;
import com.abhinav.keepsafe.home.category.email.EditEmailFragment;
import com.abhinav.keepsafe.home.category.socialnetwork.EditSocialNetworkFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CategoryFragment extends BaseFragment implements CategoryView, AdapterItemClickListener {

    @BindView(R.id.rv_category_items)
    RecyclerView rvCategoryItems;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.iv_no_view)
    ImageView ivNoView;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    private Context context;
    private CategoryPresenter mPresenter;
    private int position;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static CategoryFragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.SELECTED_CATEGORY_POSITION, position);
        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new CategoryPresenter(this);
        position = getArguments().getInt(Constants.ExtrasKey.SELECTED_CATEGORY_POSITION, -1);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(toolbar);
        showToolbarTitle(position);
        setupHeaderImage(position);
        rvCategoryItems.setLayoutManager(new LinearLayoutManager(context));
        mPresenter.initView();
        mPresenter.fetchCategoryListing(position);
        view.setOnTouchListener((v, event) -> true);
    }

    @Override
    public void setupHeaderImage(int position) {
        switch (position) {
            case 0:
                ivHeader.setBackgroundResource(R.drawable.bank_logo);
                break;
            case 1:
                ivHeader.setBackgroundResource(R.drawable.email_logo);
                break;
            case 2:
                ivHeader.setBackgroundResource(R.drawable.socialmedial_logo);
                break;
            case 3:
                ivHeader.setBackgroundResource(R.drawable.ecom_logo);
                break;
            case 4:
                ivHeader.setBackgroundResource(R.drawable.others_logo);
                break;
        }
    }

    @Override
    public void showToolbarTitle(int position) {
        switch (position) {
            case 0:
                setToolbarTitle(R.string.category_bank);
                break;
            case 1:
                setToolbarTitle(R.string.category_email);
                break;
            case 2:
                setToolbarTitle(R.string.category_social_network);
                break;
            case 3:
                setToolbarTitle(R.string.category_e_commerce);
                break;
            case 4:
                setToolbarTitle(R.string.category_others);
                break;
        }
    }

    @Override
    public void showBankListing(List<Bank> banks) {
        if (ivNoView.getVisibility() == View.VISIBLE)
            ivNoView.setVisibility(View.GONE);
        rvCategoryItems.setAdapter(getBankAdapter(banks));
    }

    @Override
    public void showEmailListings(List<Email> emails) {
        if (ivNoView.getVisibility() == View.VISIBLE)
            ivNoView.setVisibility(View.GONE);
        rvCategoryItems.setAdapter(getEmailAdapter(emails));
    }

    @Override
    public void showSocialNetworkListings(List<SocialNetwork> socialNetworks) {
        if (ivNoView.getVisibility() == View.VISIBLE)
            ivNoView.setVisibility(View.GONE);
        rvCategoryItems.setAdapter(getSocialNetworkAdapter(socialNetworks));
    }

    @Override
    public void showECommerceListings(List<ECommerce> eCommerces) {
        if (ivNoView.getVisibility() == View.VISIBLE)
            ivNoView.setVisibility(View.GONE);
        rvCategoryItems.setAdapter(getECommerceAdapter(eCommerces));
    }

    private ECommerceAdapter getECommerceAdapter(List<ECommerce> eCommerces) {
        return new ECommerceAdapter(context, eCommerces, this);
    }

    private SocialNetworkAdapter getSocialNetworkAdapter(List<SocialNetwork> socialNetworks) {
        return new SocialNetworkAdapter(context, socialNetworks, this);
    }

    private EmailAdapter getEmailAdapter(List<Email> emails) {
        return new EmailAdapter(context, emails, this);
    }

    private BankAdapter getBankAdapter(List<Bank> value) {
        return new BankAdapter(context, value, this);
    }

    @Override
    public void popFragmentOnInvalidChoice() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showNoItemView() {
        rvCategoryItems.setVisibility(View.GONE);
        ivNoView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.detachView();
    }

    @Override
    public void onItemClick(int itemPosition) {
        mPresenter.fetchCategoryItemByPosition(itemPosition, position);
    }

    @Override
    public void showBankItem(Bank bank) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_view_edit_bank, null);
        alertDialog.setView(view);
        setupBankDetails(view, bank, alertDialog);
        alertDialog.show();
    }

    private void setupBankDetails(View view, Bank bank, AlertDialog dialog) {
        TextView tvBankName = view.findViewById(R.id.tv_bank_name);
        EditText tvAccountNumber = view.findViewById(R.id.et_account_number);
        EditText tvDebitCardNumber = view.findViewById(R.id.et_debit_card_number);
        EditText tvCreditCardNumber = view.findViewById(R.id.et_credit_card_number);
        EditText tvDebitCardPin = view.findViewById(R.id.et_debit_card_pin);
        EditText tvCreditCardPin = view.findViewById(R.id.et_credit_card_pin);
        EditText tvNetBankingUserId = view.findViewById(R.id.et_net_banking_id);
        EditText tvNetBankingPassword = view.findViewById(R.id.et_net_banking_password);
        ImageView ivEdit = view.findViewById(R.id.iv_edit);

        tvBankName.setText(bank.getBankName());
        tvAccountNumber.setText(bank.getAccountNumber());
        tvDebitCardNumber.setText(bank.getDebitCardNumber());
        tvCreditCardNumber.setText(bank.getCreditCardNumber());
        tvDebitCardPin.setText(bank.getDebitCardPin());
        tvCreditCardPin.setText(bank.getCreditCardPin());
        tvNetBankingUserId.setText(bank.getCustomerId());
        tvNetBankingPassword.setText(bank.getNetBankingPassword());

        ivEdit.setOnClickListener(v -> {
            dialog.dismiss();
            mPresenter.onEditBankClicked(bank.getId());
        });

    }

    @Override
    public void addEditBankFragment(int bankId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditBankFragment.getInstance(bankId), R.id.frame_container,
                EditBankFragment.class.getSimpleName());
    }

    @Override
    public void showEmailItem(Email email) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_view_edit_email, null);
        alertDialog.setView(view);
        setupEmailDetails(view, email, alertDialog);
        alertDialog.show();
    }

    private void setupEmailDetails(View view, Email email, AlertDialog alertDialog) {
        TextView tvEmailId = view.findViewById(R.id.tv_email_id);
        EditText etPlatformName = view.findViewById(R.id.et_platform_name);
        EditText etRecoveryMail = view.findViewById(R.id.et_recovery_email);
        EditText etPassword = view.findViewById(R.id.et_email_password);
        ImageView ivEdit = view.findViewById(R.id.iv_edit);

        tvEmailId.setText(email.getEmailId());
        etPlatformName.setText(email.getPlatformName());
        etRecoveryMail.setText(email.getRecoveryEmail());
        etPassword.setText(email.getEmailPassword());

        ivEdit.setOnClickListener(v -> {
            alertDialog.dismiss();
            mPresenter.onEditEmailClicked(email.getId());
        });
    }

    @Override
    public void addEditEmailFragment(int emailId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditEmailFragment.getInstance(emailId), R.id.frame_container,
                EditEmailFragment.class.getSimpleName());
    }

    @Override
    public void showSocialNetworkItem(SocialNetwork socialNetwork) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_view_edit_social_network, null);
        alertDialog.setView(view);
        setupSocialNetworkDetails(view, socialNetwork, alertDialog);
        alertDialog.show();
    }

    private void setupSocialNetworkDetails(View view, SocialNetwork socialNetwork, AlertDialog alertDialog) {
        TextView tvPlatformName = view.findViewById(R.id.tv_platform_name);
        EditText etEmailUsed = view.findViewById(R.id.et_email_id_used);
        EditText etUserName = view.findViewById(R.id.et_social_network_username);
        EditText etPassword = view.findViewById(R.id.et_password);
        ImageView ivEdit = view.findViewById(R.id.iv_edit);

        tvPlatformName.setText(socialNetwork.getPlatformName());
        etEmailUsed.setText(socialNetwork.getEmailIdUsed());
        etUserName.setText(socialNetwork.getUsername());
        etPassword.setText(socialNetwork.getPassword());

        ivEdit.setOnClickListener(v -> {
            alertDialog.dismiss();
            mPresenter.onEditSocialNetworkClicked(socialNetwork.getId());
        });
    }

    @Override
    public void addEditSocialNetworkFragment(int socialNetworkId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditSocialNetworkFragment.getInstance(socialNetworkId), R.id.frame_container,
                EditSocialNetworkFragment.class.getSimpleName());
    }

    @Override
    public void showECommerceItem(ECommerce eCommerce) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_view_edit_ecommerce, null);
        alertDialog.setView(view);
        setupECommerceDetails(view, eCommerce, alertDialog);
        alertDialog.show();
    }

    private void setupECommerceDetails(View view, ECommerce eCommerce, AlertDialog alertDialog) {
        TextView tvPlatformName = view.findViewById(R.id.tv_platform_name);
        EditText etEmailUsed = view.findViewById(R.id.et_email_id_used);
        EditText etUserName = view.findViewById(R.id.et_ecommerce_username);
        EditText etPassword = view.findViewById(R.id.et_password);
        ImageView ivEdit = view.findViewById(R.id.iv_edit);

        tvPlatformName.setText(eCommerce.getPlatformName());
        etEmailUsed.setText(eCommerce.getEmailIdUsed());
        etUserName.setText(eCommerce.getUsername());
        etPassword.setText(eCommerce.getPassword());

        ivEdit.setOnClickListener(v -> {
            alertDialog.dismiss();
            mPresenter.onEditECommerceClicked(eCommerce.getId());
        });
    }

    @Override
    public void addEditECommerceFragment(int eCommerceId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditECommerceFragment.getInstance(eCommerceId), R.id.frame_container,
                EditECommerceFragment.class.getSimpleName());
    }
}

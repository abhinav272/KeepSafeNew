package com.abhinav.keepsafe.home.category.bank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.base.IBaseView;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.ALPHA_ANIMATIONS_DURATION;
import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_HIDE_TITLE_DETAILS;
import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR;

/**
 * Created by abhinav.sharma on 24/11/17.
 */

public class ShowBankFragment extends BaseFragment implements ShowBankView, AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_category_name)
    TextView tvCategoryName;
    @BindView(R.id.ll_title_container)
    LinearLayout llTitleContainer;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.vs_category_item)
    ViewStub vsCategoryItem;
    @BindView(R.id.cv_container)
    CardView cvContainer;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    private Context context;
    private int bankId;
    private ShowBankPresenter mPresenter;
    private EditText tvAccountNumber;
    private EditText tvDebitCardNumber;
    private EditText tvCreditCardNumber;
    private EditText tvDebitCardPin;
    private EditText tvCreditCardPin;
    private EditText tvNetBankingUserId;
    private EditText tvNetBankingPassword;
    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    public static ShowBankFragment getInstance(int bankId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.BANK_ID, bankId);
        ShowBankFragment showBankFragment = new ShowBankFragment();
        showBankFragment.setArguments(bundle);
        return showBankFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_category_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        bankId = getArguments().getInt(Constants.ExtrasKey.BANK_ID, -1);
        initCoordinatorView();
        setHasOptionsMenu(true);
        mPresenter = new ShowBankPresenter(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit){
            mPresenter.onEditClicked(bankId);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivCircle.setImageResource(R.drawable.bank_logo);
        ivHeader.setImageResource(R.drawable.img_bank_header);
        startAlphaAnimation(tvTitle, 0, View.INVISIBLE);
    }

    private void initBankStubView(View view) {
        tvAccountNumber = view.findViewById(R.id.et_account_number);
        tvDebitCardNumber = view.findViewById(R.id.et_debit_card_number);
        tvCreditCardNumber = view.findViewById(R.id.et_credit_card_number);
        tvDebitCardPin = view.findViewById(R.id.et_debit_card_pin);
        tvCreditCardPin = view.findViewById(R.id.et_credit_card_pin);
        tvNetBankingUserId = view.findViewById(R.id.et_net_banking_id);
        tvNetBankingPassword = view.findViewById(R.id.et_net_banking_password);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateBankViewStub();
        initBankStubView(view);
        mPresenter.fetchBankDetails(bankId);
    }

    private void inflateBankViewStub() {
        vsCategoryItem.setInflatedId(R.id.layout_bank_details);
        vsCategoryItem.setLayoutResource(R.layout.layout_bank_details);
        vsCategoryItem.inflate();
    }

    @Override
    public void showBankDetails(Bank bank) {
        tvTitle.setText(bank.getBankName());
        tvCategoryName.setText(bank.getBankName());
        tvAccountNumber.setText(bank.getAccountNumber());
        tvDebitCardNumber.setText(bank.getDebitCardNumber());
        tvCreditCardNumber.setText(bank.getCreditCardNumber());
        tvDebitCardPin.setText(bank.getDebitCardPin());
        tvCreditCardPin.setText(bank.getCreditCardPin());
        tvNetBankingUserId.setText(bank.getCustomerId());
        tvNetBankingPassword.setText(bank.getNetBankingPassword());
    }

    @Override
    public void showEditBankFragment(int bankId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditBankFragment.getInstance(bankId), R.id.frame_container,
                EditBankFragment.class.getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(tvTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(tvTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(llTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(llTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}

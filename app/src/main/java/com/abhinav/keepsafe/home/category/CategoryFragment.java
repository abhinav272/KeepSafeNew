package com.abhinav.keepsafe.home.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.BankAdapter;
import com.abhinav.keepsafe.adapter.ECommerceAdapter;
import com.abhinav.keepsafe.adapter.EmailAdapter;
import com.abhinav.keepsafe.adapter.SocialNetworkAdapter;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CategoryFragment extends BaseFragment implements CategoryView {

    @BindView(R.id.rv_category_items)
    RecyclerView rvCategoryItems;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.iv_no_view)
    ImageView ivNoView;
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
        rvCategoryItems.setLayoutManager(new LinearLayoutManager(context));
        mPresenter.initView();
        mPresenter.fetchCategoryListing(position);
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
        return new ECommerceAdapter(context, eCommerces);
    }

    private SocialNetworkAdapter getSocialNetworkAdapter(List<SocialNetwork> socialNetworks) {
        return new SocialNetworkAdapter(context, socialNetworks);
    }

    private EmailAdapter getEmailAdapter(List<Email> emails) {
        return new EmailAdapter(context, emails);
    }

    private BankAdapter getBankAdapter(List<Bank> value) {
        return new BankAdapter(context, value);
    }

    @Override
    public void onCategoryListingClicked(int listingPosition) {

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
}

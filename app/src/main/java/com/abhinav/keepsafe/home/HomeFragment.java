package com.abhinav.keepsafe.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.abhinav.keepsafe.BaseActivity;
import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.CTAAdapter;
import com.abhinav.keepsafe.home.category.CategoryFragment;
import com.abhinav.keepsafe.home.category.bank.AddBankFragment;
import com.abhinav.keepsafe.home.category.ecommerce.AddECommerceFragment;
import com.abhinav.keepsafe.home.category.email.AddEmailFragment;
import com.abhinav.keepsafe.home.category.socialnetwork.AddSocialNetworkFragment;
import com.abhinav.keepsafe.home.category.socialnetwork.AddSocialNetworkPresenter;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;

import java.util.List;

/**
 * Created by Abhinav on 13/05/17.
 */
public class HomeFragment extends BaseFragment implements HomeView, OnFABMenuSelectedListener, CTAAdapter.OnItemClick {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView ivNoView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private FABRevealMenu fabRevealMenu;
    private HomePresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new HomePresenter(this);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(view);
        setupToolbar(toolbar);
        setToolbarTitle(R.string.app_name);
        setHasOptionsMenu(true);
        mPresenter.initView();

    }

    private void setupUI(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.rv_ctas);
        ivNoView = view.findViewById(R.id.iv_no_view);
        fab = view.findViewById(R.id.fab);
        fabRevealMenu = view.findViewById(R.id.fabMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        try {
            if (fab != null && fabRevealMenu != null) {
                fabRevealMenu.bindAncherView(fab);
                fabRevealMenu.setOnFABMenuSelectedListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void showCategories() {
//        List<Category> categories = mPresenter.fetchAllCategories();
//        if (categories!=null && categories.size() > 0) {
//
//            if (ivNoView.getVisibility() == View.VISIBLE)
//                ivNoView.setVisibility(View.GONE);
//            if (recyclerView.getVisibility() == View.GONE)
//                recyclerView.setVisibility(View.VISIBLE);
//        } else {
//            recyclerView.setVisibility(View.GONE);
//            ivNoView.setVisibility(View.VISIBLE);
//        }
//    }

    @Override
    public void onMenuItemSelected(View view) {
        int id = (int) view.getTag();
        switch (id) {
            case R.id.action_bank:
                mPresenter.onAddBankClicked();
                break;
            case R.id.action_email:
                mPresenter.onAddEmailClicked();
                break;
            case R.id.action_social_network:
                mPresenter.onAddSocialNetworkClicked();
                break;
            case R.id.action_ecommerce:
                mPresenter.onAddECommerceClicked();
                break;
            case R.id.action_others:
                mPresenter.onAddOthersClicked();
                break;
        }
    }

    @Override
    public void showAllCTAs(List<String> ctaList) {
        recyclerView.setAdapter(getCTAAdapter(ctaList));
    }

    private CTAAdapter getCTAAdapter(List<String> ctaLists) {
        return new CTAAdapter(context, ctaLists, this);
    }

    @Override
    public void delegateClickEvent(int position) {
        onCTAClicked(position);
    }

    @Override
    public void onCTAClicked(int position) {
        mPresenter.showCategoryFragment(position);
    }

    @Override
    public void navigateToCategoryFragment(int position) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(), CategoryFragment.getInstance(position),
                R.id.frame_container, CategoryFragment.class.getSimpleName());
    }

    @Override
    public void showAddBankFragment() {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(), new AddBankFragment(),
                R.id.frame_container, AddBankFragment.class.getSimpleName());
    }

    @Override
    public void showAddEmailFragment() {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(), new AddEmailFragment(),
                R.id.frame_container, AddEmailFragment.class.getSimpleName());
    }

    @Override
    public void showAddSocialNetworkFragment() {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(), new AddSocialNetworkFragment(),
                R.id.frame_container, AddSocialNetworkFragment.class.getSimpleName());
    }

    @Override
    public void showECommerceFragment() {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(), new AddECommerceFragment(),
                R.id.frame_container, AddECommerceFragment.class.getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}

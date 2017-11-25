package com.abhinav.keepsafe.home.category.ecommerce;

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
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.home.category.bank.ShowBankPresenter;
import com.abhinav.keepsafe.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.abhinav.keepsafe.Constants.Defaults.ALPHA_ANIMATIONS_DURATION;
import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_HIDE_TITLE_DETAILS;
import static com.abhinav.keepsafe.Constants.Defaults.PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR;

/**
 * Created by abhinav.sharma on 25/11/17.
 */

public class ShowECommerceFragment extends BaseFragment implements ShowECommerceView, AppBarLayout.OnOffsetChangedListener {

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
    @BindView(R.id.vs_category_item)
    ViewStub vsCategoryItem;
    @BindView(R.id.cv_container)
    CardView cvContainer;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    Unbinder unbinder;
    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;
    private Context context;
    private ShowECommercePresenter mPresenter;
    private int eCommerceId;
    private EditText etEmailUsed, etUserName, etPassword;

    public static ShowECommerceFragment getInstance(int eCommerceId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ExtrasKey.ECOMMERCE_ID, eCommerceId);
        ShowECommerceFragment showECommerceFragment = new ShowECommerceFragment();
        showECommerceFragment.setArguments(bundle);
        return showECommerceFragment;
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
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new ShowECommercePresenter(this);
        eCommerceId = getArguments().getInt(Constants.ExtrasKey.ECOMMERCE_ID);
        initCoordinatorView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateECommerceViewStub();
        initECommerceStubView(view);
        mPresenter.fetchECommerceDetails(eCommerceId);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            mPresenter.onEditClicked(eCommerceId);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCoordinatorView() {
        setupToolbar(toolbar);
        toolbar.setTitle(null);
        appbar.addOnOffsetChangedListener(this);
        ivCircle.setImageResource(R.drawable.ecom_logo);
        ivHeader.setImageResource(R.drawable.img_ecommerce_header);
        startAlphaAnimation(tvTitle, 0, View.INVISIBLE);
    }

    private void initECommerceStubView(View view) {
        etEmailUsed = view.findViewById(R.id.et_email_id_used);
        etUserName = view.findViewById(R.id.et_ecommerce_username);
        etPassword = view.findViewById(R.id.et_password);
    }

    private void inflateECommerceViewStub() {
        vsCategoryItem.setInflatedId(R.id.layout_ecommerce_details);
        vsCategoryItem.setLayoutResource(R.layout.layout_ecommerce_details);
        vsCategoryItem.inflate();
    }

    @Override
    public void popFragment() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void showECommerceDetails(ECommerce eCommerce) {
        tvTitle.setText(eCommerce.getPlatformName());
        tvCategoryName.setText(eCommerce.getPlatformName());
        etEmailUsed.setText(eCommerce.getEmailIdUsed());
        etUserName.setText(eCommerce.getUsername());
        etPassword.setText(eCommerce.getPassword());
    }

    @Override
    public void showEditECommerceFragment(int ecommerceId) {
        ((BaseActivity) context).addFragmentWithBackStack(getFragmentManager(),
                EditECommerceFragment.getInstance(eCommerceId), R.id.frame_container,
                EditECommerceFragment.class.getSimpleName());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        unbinder.unbind();
    }
}

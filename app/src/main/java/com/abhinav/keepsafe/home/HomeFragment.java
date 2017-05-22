package com.abhinav.keepsafe.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 13/05/17.
 */
public class HomeFragment extends BaseFragment implements HomeContract.IView {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView ivNoView;
    private Toolbar toolbar;
    private HomeContract.IPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new HomePresenter();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(view);
        setupToolbar(toolbar);
        setToolbarTitle(R.string.home_fragment_title);
        setHasOptionsMenu(true);
        showCategories();

    }

    private void setupUI(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);
        ivNoView = (ImageView) view.findViewById(R.id.iv_no_view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                showMessage("Add category");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(HomeContract.IPresenter presenter) {

    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showCategories() {
        List<Category> categories = mPresenter.fetchAllCategories();
        if (categories.size() > 0) {

            if (ivNoView.getVisibility() == View.VISIBLE)
                ivNoView.setVisibility(View.GONE);
            if (recyclerView.getVisibility() == View.GONE)
                recyclerView.setVisibility(View.VISIBLE);



        } else {
            recyclerView.setVisibility(View.GONE);
            ivNoView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showCategoryListing() {

    }
}

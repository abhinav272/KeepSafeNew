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
import android.widget.Toolbar;

import com.abhinav.keepsafe.BaseFragment;
import com.abhinav.keepsafe.R;

/**
 * Created by Abhinav on 13/05/17.
 */
public class HomeFragment extends BaseFragment implements HomeContract.IView {

    private Context context;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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

    }

    @Override
    public void showCategoryListing() {

    }
}

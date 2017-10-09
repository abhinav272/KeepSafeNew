package com.abhinav.keepsafe;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.abhinav.keepsafe.base.*;

/**
 * Created by Abhinav on 23/04/17.
 */
public class BaseFragment extends Fragment implements com.abhinav.keepsafe.base.IBaseView {

    public void setupToolbar(Toolbar toolbar) {
        getActivity().setActionBar(toolbar);
    }

    public void setToolbarTitle(int titleResId) {
        getActivity().getActionBar().setTitle(titleResId);
    }

    @Override
    public void showSnackbar(String msg) {
        Snackbar.make(null, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showToastLong(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastShort(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}

package com.abhinav.keepsafe;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
import android.widget.Toolbar;

/**
 * Created by Abhinav on 23/04/17.
 */
public class BaseFragment extends Fragment {

    public <T> T checkNotNull(T presenter) {
        if (presenter == null) {
            throw new NullPointerException("IPresenter cannot be null");
        } else return presenter;
    }

    public void showMessageOnSnackBar(String msg){
        Snackbar.make(null, msg, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void setupToolbar(Toolbar toolbar){
        getActivity().setActionBar(toolbar);
    }
}

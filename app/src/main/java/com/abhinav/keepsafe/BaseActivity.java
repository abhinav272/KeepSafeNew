package com.abhinav.keepsafe;

import android.app.Activity;
import android.app.FragmentManager;

/**
 * Created by Abhinav on 23/04/17.
 */
public class BaseActivity extends Activity {

    public void addFragmentWithBackStack(FragmentManager fragmentManager, BaseFragment baseFragment, int containerResId, String tag) {
        fragmentManager.beginTransaction()
                .add(containerResId, baseFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void addFragment(FragmentManager fragmentManager, BaseFragment baseFragment, int containerResId, String tag) {
        fragmentManager.beginTransaction()
                .add(containerResId, baseFragment, tag)
                .commit();
    }

    public void replaceFragmentWithBackStack(FragmentManager fragmentManager, BaseFragment baseFragment, int containerResId, String tag) {
        fragmentManager.beginTransaction()
                .replace(containerResId, baseFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void replaceFragment(FragmentManager fragmentManager, BaseFragment baseFragment, int containerResId, String tag) {
        fragmentManager.beginTransaction()
                .replace(containerResId, baseFragment, tag)
                .commit();
    }
}

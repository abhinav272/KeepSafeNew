package com.abhinav.keepsafe;

import android.app.FragmentManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

/**
 * Created by Abhinav on 23/04/17.
 */
public class BaseActivity extends AppCompatActivity {

    public void addFragmentWithBackStack(FragmentManager fragmentManager, BaseFragment baseFragment, int containerResId, String tag) {
        fragmentManager.beginTransaction()
                .add(containerResId, baseFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void addFragmentWithBackStackAndSharedElement(FragmentManager fragmentManager,
                                                         BaseFragment currentFragment,
                                                         BaseFragment nextFragment,
                                                         int containerResId, String tag, View sharedElement) {

        /**
         * Transition Animation, Little detailed transition
         */
//        // Exit for Previous Fragment
//        Fade exitFade = new Fade();
//        exitFade.setDuration(Constants.Defaults.FADE_DEFAULT_TIME);
//        currentFragment.setExitTransition(exitFade);
//        currentFragment.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
//
//        // Shared Elements Transition
//        TransitionSet enterTransitionSet = new TransitionSet();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            enterTransitionSet.addTransition(TransitionInflater.from(currentFragment.getContext()).inflateTransition(android.R.transition.move));
//        } else
//            enterTransitionSet.addTransition(TransitionInflater.from(currentFragment.getActivity()).inflateTransition(android.R.transition.move));
//        enterTransitionSet.setDuration(Constants.Defaults.MOVE_DEFAULT_TIME);
//        enterTransitionSet.setStartDelay(Constants.Defaults.FADE_DEFAULT_TIME);
//        nextFragment.setSharedElementEnterTransition(enterTransitionSet);
//
//        // 3. Enter Transition for New Fragment
//        Fade enterFade = new Fade();
//        enterFade.setStartDelay(Constants.Defaults.MOVE_DEFAULT_TIME + Constants.Defaults.FADE_DEFAULT_TIME);
//        enterFade.setDuration(Constants.Defaults.FADE_DEFAULT_TIME);
//        nextFragment.setEnterTransition(enterFade);

        /**
         * Yet Another simple Animation setup
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            currentFragment.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            currentFragment.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

            nextFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            nextFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
        }

        fragmentManager.beginTransaction()
                .addSharedElement(sharedElement, sharedElement.getTransitionName())
                .replace(containerResId, nextFragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();

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

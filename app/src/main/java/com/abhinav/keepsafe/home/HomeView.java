package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.base.IBaseView;

import java.util.List;

/**
 * Created by Abhinav on 13/05/17.
 */
public interface HomeView extends IBaseView{

    void showAllCTAs(List<String> ctaList);
    void onCTAClicked(int position);
    void navigateToCategoryFragment(int position);
    void showAddBankFragment();
    void showAddEmailFragment();
}

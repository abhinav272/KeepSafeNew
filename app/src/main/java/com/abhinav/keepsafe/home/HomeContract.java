package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.IBasePresenter;
import com.abhinav.keepsafe.IBaseView;

/**
 * Created by Abhinav on 13/05/17.
 */
public interface HomeContract {

    interface IView extends IBaseView<IPresenter> {
        void showMessage(String msg);
        void showCategories();
        void showCategoryListing();
    }

    interface IPresenter extends IBasePresenter {
        void fetchAllCategories();
        void addNewCategories();
        void fetchCategoriesListing();
    }
}

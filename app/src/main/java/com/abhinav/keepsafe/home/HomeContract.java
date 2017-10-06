package com.abhinav.keepsafe.home;

import com.abhinav.keepsafe.IBasePresenter;
import com.abhinav.keepsafe.IBaseView;
import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 13/05/17.
 */
public interface HomeContract {

    interface IView extends IBaseView<IPresenter> {
        void showMessage(String msg);
        void showCategories();
        void showCategoryListingFragment(int categoryId);
        void showAddListingFragment(int categoryId);
    }

    interface IPresenter extends IBasePresenter {
        List<Category> fetchAllCategories();

    }
}

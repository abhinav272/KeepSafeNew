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
        void showAllCTAs();
    }

    interface IPresenter extends IBasePresenter {

    }
}

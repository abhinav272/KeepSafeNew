package com.abhinav.keepsafe.base;

import java.lang.ref.SoftReference;

/**
 * Created by abhinav.sharma on 09/10/17.
 */

public abstract class BasePresenter<T extends IBaseView> implements BaseModelListener {

    private SoftReference<T> view;

    public BasePresenter(T view) {
        this.view = new SoftReference<T>(view);
        setModel();
    }

    public void attachView(T view) {
        this.view = new SoftReference<T>(view);
        setModel();
    }

    public T getView() {
        return (view == null) ? null : view.get();
    }

    public void detachView() {
        view = null;
        destroy();
    }

    protected abstract void setModel();

    protected abstract void destroy();

    protected abstract void initView();

    @Override
    public void noResultFound() {
        /**
         * Add any custom handling if required
         * */
    }
}

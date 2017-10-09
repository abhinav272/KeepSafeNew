package com.abhinav.keepsafe.base;

import com.abhinav.keepsafe.data.DataManager;

import java.lang.ref.SoftReference;

/**
 * Created by abhinav.sharma on 09/10/17.
 */

public abstract class BaseModel<T extends BaseModelListener> {

    public DataManager dataManager;
    private SoftReference<T> listener;

    public BaseModel(T listener) {
        this.listener = new SoftReference<T>(listener);
        this.dataManager = DataManager.getInstance();
    }

    public void attachListener(T listener) {
        this.listener = new SoftReference<T>(listener);
    }

    public void detachListener(){
        this.listener = null;
    }

    public T getListener() {
        if (listener != null) {
            return listener.get();
        }
        return null;
    }
}

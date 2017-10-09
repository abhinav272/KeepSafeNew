package com.abhinav.keepsafe.base;

/**
 * Created by abhinav.sharma on 09/10/17.
 */

/**
 * This is base listener to provide null or empty result set
 * Impl of this may be used to get Async result set from DB queries
 * */
public interface BaseModelListener {

    void noResultFound();
}

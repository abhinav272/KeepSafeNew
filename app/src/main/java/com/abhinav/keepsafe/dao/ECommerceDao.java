package com.abhinav.keepsafe.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.entities.ECommerce;

import java.util.List;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

@Dao
public interface ECommerceDao {

    @Query("SELECT * FROM " + Constants.Database.T_ECOMMERCE)
    LiveData<List<ECommerce>> getAllECommerceAccounts();

    @Query("SELECT * FROM t_ecommerce WHERE id = :id")
    LiveData<ECommerce> getECommerceDetails(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long addECommerce(ECommerce eCommerce);

    @Update
    void updateECommerce(ECommerce eCommerce);

    @Delete
    void deleteECommerce(ECommerce eCommerce);
}

package com.abhinav.keepsafe.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.entities.Bank;

import java.util.List;

/**
 * Created by abhinav.sharma on 07/10/17.
 */

@Dao
public interface BankDao {

    @Query("SELECT * FROM " + Constants.Database.T_BANK)
    LiveData<List<Bank>> getAllBanks();

    @Query("SELECT * FROM t_bank WHERE id = :id")
    LiveData<Bank> getBankDetails(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addBank(Bank bank);

    @Update
    void updateBank(Bank bank);

    @Delete
    void deleteBank(Bank bank);
}

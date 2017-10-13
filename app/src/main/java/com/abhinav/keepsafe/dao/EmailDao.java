package com.abhinav.keepsafe.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.entities.Email;

import java.util.List;

/**
 * Created by abhinav.sharma on 07/10/17.
 */

@Dao
public interface EmailDao {

    @Query("SELECT * FROM " + Constants.Database.T_EMAIL)
    LiveData<List<Email>> getAllEmails();

    @Query("SELECT * FROM t_email WHERE id = :id")
    LiveData<Email> getEmailDetails(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long addEmail(Email email);

    @Update
    void updateEmail(Email email);

    @Delete
    void deleteEmail(Email email);
}

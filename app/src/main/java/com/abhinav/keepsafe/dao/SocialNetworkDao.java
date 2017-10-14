package com.abhinav.keepsafe.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

@Dao
public interface SocialNetworkDao {
    @Query("SELECT * FROM " + Constants.Database.T_SOCIAL_NETWORK)
    LiveData<List<SocialNetwork>> getAllSocialNetworkAccounts();

    @Query("SELECT * FROM t_social_network WHERE id = :id")
    LiveData<SocialNetwork> getSocialNetworkDetails(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long addSocialNetwork(SocialNetwork socialNetwork);

    @Update
    void updateSocialNetwork(SocialNetwork socialNetwork);

    @Delete
    void deleteSocialNetwork(SocialNetwork socialNetwork);
}

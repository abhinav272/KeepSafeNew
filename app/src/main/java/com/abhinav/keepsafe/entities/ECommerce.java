package com.abhinav.keepsafe.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.abhinav.keepsafe.Constants;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

@Entity(tableName = Constants.Database.T_ECOMMERCE)
public class ECommerce {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "platform_name")
    private String platformName;
    @ColumnInfo(name = "email_id_used")
    private String emailIdUsed;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "username")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getEmailIdUsed() {
        return emailIdUsed;
    }

    public void setEmailIdUsed(String emailIdUsed) {
        this.emailIdUsed = emailIdUsed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

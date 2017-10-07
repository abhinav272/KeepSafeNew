package com.abhinav.keepsafe.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by abhinav.sharma on 07/10/17.
 */

@Entity
public class Email {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email_id")
    private String emailId;

    @ColumnInfo(name = "email_password")
    private String emailPassword;

    @ColumnInfo(name = "recovery_email")
    private String recoveryEmail;

    @ColumnInfo(name = "platform_name")
    private String platformName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}

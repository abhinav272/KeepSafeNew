package com.abhinav.keepsafe.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by abhinav.sharma on 07/10/17.
 */

@Entity
public class Bank {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "bank_name")
    private String bankName;

    @ColumnInfo(name = "account_type")
    private String accountType;

    @ColumnInfo(name = "account_number")
    private String accountNumber;

    @ColumnInfo(name = "debit_card_number")
    private String debitCardNumber;

    @ColumnInfo(name = "credit_card_number")
    private String creditCardNumber;

    @ColumnInfo(name = "debit_card_pin")
    private String debitCardPin;

    @ColumnInfo(name = "credit_card_pin")
    private String creditCardPin;

    @ColumnInfo(name = "customer_id")
    private String customerId;

    @ColumnInfo(name = "net_banking_password")
    private String netBankingPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(String debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getDebitCardPin() {
        return debitCardPin;
    }

    public void setDebitCardPin(String debitCardPin) {
        this.debitCardPin = debitCardPin;
    }

    public String getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(String creditCardPin) {
        this.creditCardPin = creditCardPin;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNetBankingPassword() {
        return netBankingPassword;
    }

    public void setNetBankingPassword(String netBankingPassword) {
        this.netBankingPassword = netBankingPassword;
    }
}

package com.mavens.bank;

import com.maven.bank.dataStore.AccountType;

import java.math.BigDecimal;

public class Account {
    private long accountNumber;
    private AccountType typeOfAccount;
    private BigDecimal balance;
    private String accountPin;

    public Account(int i, AccountType savings) {
    }

//    public Account(long accountNumber, AccountType accountTypes) {
//       this.accountNumber = accountNumber;
//       this.typeOfAccount = accountTypes;
//    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(AccountType typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }
}

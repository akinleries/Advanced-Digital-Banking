package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.Exceptions.MavenBankTransactionException;
import com.maven.bank.dataStore.AccountType;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;

import java.math.BigDecimal;

public interface AccountService {


    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankException;

    public BigDecimal deposit(BigDecimal amount, long accountNumber) throws MavenBankTransactionException, MavenBankException;

    public Account findAccount(long accountNumber)throws MavenBankException;
//    public Account findAccount(Customer customer, long accountNumber)throws MavenBankException;




}

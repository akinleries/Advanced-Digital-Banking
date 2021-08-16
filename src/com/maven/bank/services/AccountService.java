package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.dataStore.AccountType;
import com.mavens.bank.Customer;

public interface AccountService {
    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankException;


}

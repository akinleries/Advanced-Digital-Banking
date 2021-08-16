package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;

public class AccountServiceImplement implements AccountService {
    @Override
    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankException {

        if (theCustomer == null || type == null) {
            throw new MavenBankException("Customer and type required to open new account");
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        newAccount.setTypeOfAccount(type);
        CustomerRepo.getCustomers().put(theCustomer.getBvn(), theCustomer);
        theCustomer.getAccounts().add(newAccount);

        return newAccount.getAccountNumber();
    }

    private boolean accountTypeExist(Customer acustomer, AccountType type) {
        boolean accountTypeExists = false;

        for (Account customerAccount : acustomer.getAccounts()) {
            if (customerAccount.getTypeOfAccount() == type) ;
            accountTypeExists = true;
            break;

        }
        return accountTypeExists;
    }
}
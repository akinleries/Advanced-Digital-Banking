package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.Exceptions.MavenBankTransactionException;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;

import java.math.BigDecimal;

public class AccountServiceImplement implements AccountService {
    @Override
    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankException {

        if (theCustomer == null || type == null) {
            throw new MavenBankException("Customer and type required to open new account");
        }
        if (accountTypeExist(theCustomer, type)){
            throw new MavenBankException("account already exist");
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        newAccount.setTypeOfAccount(type);
        CustomerRepo.getCustomers().put(theCustomer.getBvn(), theCustomer);
        theCustomer.getAccounts().add(newAccount);

        return newAccount.getAccountNumber();
    }
    @Override
    public BigDecimal deposit(BigDecimal amount, long accountNumber)throws MavenBankTransactionException, MavenBankException {
        BigDecimal newBalance = BigDecimal.ZERO;
        Account depositAccount = findAccount(accountNumber);
       newBalance = depositAccount.getBalance().add(amount);
       depositAccount.setBalance(newBalance);

        return newBalance;
    }

    @Override
    public Account findAccount(long accountNumber)throws MavenBankException{

        Account foundAccount = null;
        boolean accountFound = false;

        for (Customer customer : CustomerRepo.getCustomers().values()){
            for (Account account : customer.getAccounts()){
                if(account.getAccountNumber() == accountNumber){
                    foundAccount = account;
                    accountFound = true;
                    break;
                }
            }
            if (accountFound){
                break;
            }
        }

     return foundAccount;
    }

//    @Override
//    public Account findAccount(Customer customer, long accountNumber) throws MavenBankException {
//        return null;
//    }


    private boolean accountTypeExist(Customer customer, AccountType type) {
        boolean accountTypeExists = false;

        for (Account customerAccount : customer.getAccounts()) {
            if (customerAccount.getTypeOfAccount() == type) ;
            accountTypeExists = true;
            break;

        }
        return accountTypeExists;
    }
}
package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplementTest {
    private AccountService accountService;
    private Customer kingsley;
    private Account kingsleyAccount;
    private Customer bobo;

    @BeforeEach
    void setUp() {
        bobo = new Customer();
        kingsley = new Customer();
        kingsley.setBvn(BankService.generateBVN());
        accountService = new AccountServiceImplement();

        kingsley.setEmail("kingsley@maven.com");
        kingsley.setFirstName("kingsley");
        kingsley.setLastName("albert");
        kingsley.setPhoneNumber("2345678675432");

    }
    @Test
    void openAccount() {

        try {
            assertTrue(kingsley.getAccounts().isEmpty());
            assertTrue(CustomerRepo.getCustomers().isEmpty());
            assertEquals(0, BankService.getCurrentAccountNumber());

          long  newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);

            assertFalse(CustomerRepo.getCustomers().isEmpty());
            assertEquals(1, BankService.getCurrentAccountNumber());
            assertTrue(CustomerRepo.getCustomers().containsKey(kingsley.getBvn()));
            assertFalse(kingsley.getAccounts().isEmpty());
            assertEquals(newAccountNumber, kingsley.getAccounts().get(0).getAccountNumber());
        } catch (MavenBankException e) {
            e.printStackTrace();
        }

    }

    @Test
    void openAccountWithNoCustomer() {
        assertThrows(MavenBankException.class, ()-> accountService.openAccount(kingsley,null));

    }

    @Test
    void openAccountWithNoAccountType() {
        assertThrows(MavenBankException.class, ()-> accountService.openAccount(kingsley,null));

    }

    @Test
    void openSameTypeOfAccountForSameCustomer() {
         try {
             long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
             assertFalse(CustomerRepo.getCustomers().isEmpty());
             assertEquals(1, BankService.getCurrentAccountNumber());
             assertTrue(CustomerRepo.getCustomers().containsKey(kingsley.getBvn()));
             assertFalse(kingsley.getAccounts().isEmpty());
             assertEquals(newAccountNumber, kingsley.getAccounts().get(0).getAccountNumber() );


         } catch (MavenBankException e) {
             e.printStackTrace();
         }
         //assertThrows(MavenBankException.class,()-> accountService.openAccount(kingsley,AccountType.SAVINGS));

    }

    @Test
    void openDifferentTypeOfAccountForSameCustomer(){
        try {
            long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
            assertFalse(CustomerRepo.getCustomers().isEmpty());
            assertEquals(1, BankService.getCurrentAccountNumber());
            assertTrue(CustomerRepo.getCustomers().containsKey(kingsley.getBvn()));
            assertEquals(1,kingsley.getAccounts().size());
            assertEquals(newAccountNumber, kingsley.getAccounts().get(0).getAccountNumber() );

            newAccountNumber = accountService.openAccount(kingsley, AccountType.CURRENT);
            assertEquals(2, BankService.getCurrentAccountNumber());
            assertEquals(2,kingsley.getAccounts().size());
            assertEquals(newAccountNumber, kingsley.getAccounts().get(1).getAccountNumber() );

        } catch (MavenBankException e) {
            e.printStackTrace();
        }
    }
    @Test
    void openSavingsAccountForNewCustomer() {
        try {
            long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
            assertFalse(CustomerRepo.getCustomers().isEmpty());
            assertEquals(1, BankService.getCurrentAccountNumber());
            assertTrue(CustomerRepo.getCustomers().containsKey(kingsley.getBvn()));
            assertFalse(kingsley.getAccounts().isEmpty());
            assertEquals(newAccountNumber, kingsley.getAccounts().get(0).getAccountNumber() );

            newAccountNumber = accountService.openAccount(bobo, AccountType.SAVINGS);
            assertEquals(2, CustomerRepo.getCustomers().size());
            assertEquals(2, BankService.getCurrentAccountNumber());
            assertEquals(newAccountNumber, bobo.getAccounts().get(0).getAccountNumber());



        } catch (MavenBankException e) {
            e.printStackTrace();
        }


    }

}
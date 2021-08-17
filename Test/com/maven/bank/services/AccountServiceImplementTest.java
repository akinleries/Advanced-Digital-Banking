package com.maven.bank.services;

import com.maven.bank.Exceptions.MavenBankException;
import com.maven.bank.Exceptions.MavenBankTransactionException;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplementTest {
    private AccountService accountService;
    private Customer kingsley;
    private Account kingsleyAccount;
    private Customer bobo;
    private Customer dada;



    @BeforeEach
    void setUp() {
        bobo = new Customer();
        kingsley = new Customer();
        dada = new Customer();
        kingsley.setBvn(BankService.generateBVN());
        accountService = new AccountServiceImplement();

//        kingsley.setEmail("kingsley@maven.com");
//        kingsley.setFirstName("kingsley");
//        kingsley.setLastName("albert");
//        kingsley.setPhoneNumber("2345678675432");

        dada.setEmail("kingsley@maven.com");
        dada.setFirstName("kingsley");
        dada.setLastName("albert");
        dada.setPhoneNumber("2345678675432");

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
        Optional<Customer> kingsleyOptional  = CustomerRepo.getCustomers().values().stream().findFirst();
        Customer john = (kingsleyOptional.isEmpty()) ? null : kingsleyOptional.get();
        assertEquals(3, BankService.getCurrentAccountNumber());
        assertNotNull(kingsley);
        assertNotNull(kingsley.getAccounts());
        assertFalse(kingsley.getAccounts().isEmpty());
        assertEquals(AccountType.SAVINGS, kingsley.getAccounts().get(0).getTypeOfAccount());

        assertThrows()
//         try {
//             long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
//             assertFalse(CustomerRepo.getCustomers().isEmpty());
//             assertEquals(1, BankService.getCurrentAccountNumber());
//             assertTrue(CustomerRepo.getCustomers().containsKey(kingsley.getBvn()));
//             assertFalse(kingsley.getAccounts().isEmpty());
//             assertEquals(newAccountNumber, kingsley.getAccounts().get(0).getAccountNumber() );
//
//
//         } catch (MavenBankException e) {
//             e.printStackTrace();
//         }
//         assertThrows(MavenBankException.class,()-> accountService.openAccount(kingsley, AccountType.SAVINGS));
//        assertEquals(1, BankService.getCurrentAccountNumber());

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
    @Test
    void deposit(){
        try {
            long newAccountNumber = accountService.openAccount(dada, AccountType.SAVINGS);
            assertFalse(CustomerRepo.getCustomers().isEmpty());
            assertNull(dada.getAccounts().get(0).getBalance());
            long accountNumber = dada.getAccounts().get(0).getAccountNumber();
            BigDecimal accountBalance = accountService.deposit(new BigDecimal(50000), accountNumber);
            assertNotNull(kingsley.getAccounts().get(0).getBalance());

        } catch (MavenBankTransactionException e) {
            e.printStackTrace();
        } catch (MavenBankException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    void findAccount(){
//        long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
//        long newAccountNumber = accountService.openAccount(kingsley, AccountType.CURRENT);
//        long newAccountNumber = accountService.openAccount(kingsley, AccountType.SAVINGS);
//        assertFalse(CustomerRepo.getCustomers().isEmpty());
//        assertEquals(2, CustomerRepo.getCustomers().size());
//        assertEquals(3, BankService.getCurrentAccountNumber());
//
//        Account kingsleyCurrentAccount = accountService.findAccount(2);
//        assertNotNull(kingsleyCurrentAccount);
//
//
//
//    }

    @Test
    void findAccount(){
        try {
            Account kingsleyCurrentAccount = accountService.findAccount(2);
            assertNotNull(kingsleyCurrentAccount);
            assertEquals(2, kingsleyCurrentAccount.getAccountNumber());
            assertEquals(AccountType.CURRENT, kingsleyCurrentAccount.getTypeOfAccount());


        } catch (MavenBankException e) {
            e.printStackTrace();
        }
    }
}
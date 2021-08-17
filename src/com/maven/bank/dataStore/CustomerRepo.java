package com.maven.bank.dataStore;

import com.maven.bank.services.AccountServiceImplement;
import com.maven.bank.services.BankService;
import com.mavens.bank.Account;
import com.mavens.bank.Customer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepo {
    private static Map<Long, Customer> customer = new HashMap<>();


    public static Map<Long, Customer> getCustomers() {
        return customer;
    }

    private void setCustomer(Map<Long, Customer> customer) {
        CustomerRepo.customer = customer;
    }

    static {
        Customer kingsley = new Customer();
        kingsley.setBvn(BankService.generateBVN());
        kingsley.setEmail("kingsley@maven.com");
        kingsley.setFirstName("kingsley");
        kingsley.setLastName("albert");
        kingsley.setPhoneNumber("2345678675432");

        Account kingsleyAccount = new Account(1, AccountType.SAVINGS);
        kingsley.getAccounts().add(kingsleyAccount);

        Account kingsleyAccount1= new Account(2, AccountType.SAVINGS), new BigDecimal(50000000);
        kingsley.getAccounts().add(kingsleyAccount);
        CustomerRepo.put(kingsley.getBvn(), kingsley);

        Customer bobo = new Customer();
        kingsley.setBvn(BankService.generateBVN());
        kingsley.setEmail("kingsley@maven.com");
        kingsley.setFirstName("kingsley");
        kingsley.setLastName("albert");
        kingsley.setPhoneNumber("2345678675432");

        Account boboAccount = new Account(2, AccountType.SAVINGS), new BigDecimal(50000000);
        kingsley.getAccounts().add(boboAccount);
        Customer.put(bobo.getBvn(), bobo);

    }
}

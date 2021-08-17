package com.mavens.bank;

import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.maven.bank.services.BankService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class AccountTest {
    Customer kingsley;
    Account kingsleyAccount;
    @BeforeEach

    void setUp(){

        kingsley = new Customer();
        kingsleyAccount = new Account();
    }
    @Test
    void openAccount(){
    kingsley.setBvn(BankService.generateBVN());
    kingsley.setEmail("kingsley@maven.com");
    kingsley.setFirstName("kingsley");
    kingsley.setLastName("albert");
    kingsley.setPhoneNumber("2345678675432");
    kingsleyAccount.setAccountNumber(BankService.generateAccountNumber());
    kingsleyAccount.setTypeOfAccount(AccountType.SAVINGS);
    kingsleyAccount.setBalance(new BigDecimal(5000));
    kingsleyAccount.setAccountPin("1000");
    kingsleyAccount.setAccountNumber(BankService.generateAccountNumber());
    kingsley.getAccounts().add(kingsleyAccount);

    assertTrue(CustomerRepo.getCustomers().isEmpty());


    }
}

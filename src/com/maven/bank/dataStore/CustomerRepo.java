package com.maven.bank.dataStore;

import com.mavens.bank.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepo {
    private static Map<Long, Customer> customer = new HashMap<>();


    public static Map<Long, Customer> getCustomers() {
        return customer;
    }

    private void setCustomer(Map<Long, Customer> customer) {
        this.customer = customer;
    }
}

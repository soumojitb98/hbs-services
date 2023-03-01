package com.hbs.repository;


import com.hbs.model.Customer;

/**
 * Created By Soumojit_Basak on 26-02-2023
 */
public interface CustomerRepo {

    Integer getCustomerByUserName(String userName);

    Customer findByEmail(String email);

    void addCustomerDetails(Customer cust);
}

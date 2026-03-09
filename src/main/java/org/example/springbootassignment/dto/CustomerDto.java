package org.example.springbootassignment.dto;

import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Customer;

import java.util.List;

public record CustomerDto (
        long  customerId,
        String nic,
        String customerName,
        String emailAddress,
        String phoneNumber,
        boolean isActive,
        List<BankAccount> accounts
){

    public  static CustomerDto from(Customer customer){
        return new  CustomerDto (customer.getCustomerId(),
                customer.getNicNumber(),
                customer.getCustomerName(),
                customer.getEmailAddress(),
                customer.getPhoneNumber(),
                customer.isActive(),
                customer.getAccounts());
    }

}

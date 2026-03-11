package org.example.springbootassignment.dto.customerDto;

import org.example.springbootassignment.model.Customer;

public record CustomerSummeryDto(
        long customerId,
        String customerName,
        String emailAddress
) {
    public static CustomerSummeryDto from(Customer customer) {
        return new CustomerSummeryDto(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getEmailAddress()
        );}
}

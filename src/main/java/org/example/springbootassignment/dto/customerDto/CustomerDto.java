package org.example.springbootassignment.dto.customerDto;

import org.example.springbootassignment.dto.bankAccountDto.BankAccountSummaryDto;
import org.example.springbootassignment.model.Customer;

import java.util.List;

public record CustomerDto (
        long  customerId,
        String nic,
        String customerName,
        String emailAddress,
        String phoneNumber,
        boolean isActive,
        List<BankAccountSummaryDto> accounts
){

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getNicNumber(),
                customer.getCustomerName(),
                customer.getEmailAddress(),
                customer.getPhoneNumber(),
                customer.isActive(),
                customer.getAccounts().stream()
                        .map(BankAccountSummaryDto::from)
                        .toList()
        );
    }
    }

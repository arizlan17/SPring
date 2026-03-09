package org.example.springbootassignment.dto;

import org.example.springbootassignment.enums.AccountType;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Customer;
import org.example.springbootassignment.model.Transactions;

import java.time.LocalDateTime;
import java.util.List;

public record CreatedBankAccountDto (
    long accountNumber,
    Customer owner,
    double accountBalance,
    AccountType accountType,
    List<Transactions> transactionHistory,
    LocalDateTime createdAt
){
    public static CreatedBankAccountDto from(BankAccount bankAccount){
        return new CreatedBankAccountDto(
                bankAccount.getAccountNumber(),
                bankAccount.getOwner(),
                bankAccount.getAccountBalance(),
                bankAccount.getAccountType(),
                bankAccount.getTransactionHistory(),
                bankAccount.getCreatedAt());
    }
}

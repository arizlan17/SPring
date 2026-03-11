package org.example.springbootassignment.dto.bankAccountDto;

import org.example.springbootassignment.dto.customerDto.CustomerSummeryDto;
import org.example.springbootassignment.enums.AccountType;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public record CreatedBankAccountDto (
    long accountNumber,
    double accountBalance,
    AccountType accountType,
    List<Transaction> transactionHistory,
    LocalDateTime createdAt,
    CustomerSummeryDto owner
    ){
    public static CreatedBankAccountDto from(BankAccount bankAccount){
        return new CreatedBankAccountDto(
                bankAccount.getAccountNumber(),
                bankAccount.getAccountBalance(),
                bankAccount.getAccountType(),
                bankAccount.getTransactionHistory(),
                bankAccount.getCreatedAt(),
                CustomerSummeryDto.from(bankAccount.getOwner())
                );
    }
}

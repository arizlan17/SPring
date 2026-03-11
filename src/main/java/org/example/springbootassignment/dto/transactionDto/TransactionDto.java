package org.example.springbootassignment.dto.transactionDto;


import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Transaction;

import java.time.LocalDateTime;

public record TransactionDto(
        String transactionId,
        LocalDateTime transactionDate,
        TransactionType transactionType,
        long accountNumber,
        double transactionAmount,
        String description

)
 {
    public static TransactionDto from (Transaction transaction){
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getTransactionDate(),
                transaction.getTransactionType(),
                transaction.getBankAccount().getAccountNumber(),
                transaction.getTransactionAmount(),
                transaction.getDescription()
        );
    }
}

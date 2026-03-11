package org.example.springbootassignment.dto.transactionDto;

import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.Transaction;

import java.time.LocalDateTime;

public record TransactionSummaryDto(
        String transactionId,
        LocalDateTime transactionDate,
        TransactionType transactionType,
        double transactionAmount,
        long accountNumber,
        String description
) {
    public static TransactionSummaryDto from(Transaction transaction) {
        return new TransactionSummaryDto(
                transaction.getTransactionId(),
                transaction.getTransactionDate(),
                transaction.getTransactionType(),
                transaction.getTransactionAmount(),
                transaction.getBankAccount().getAccountNumber(),
                transaction.getDescription());

    }


}

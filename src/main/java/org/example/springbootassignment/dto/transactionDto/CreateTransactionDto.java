package org.example.springbootassignment.dto.transactionDto;

import jakarta.validation.constraints.NotBlank;
import org.example.springbootassignment.enums.TransactionType;

public record CreateTransactionDto(
        @NotBlank(message = "Transaction Type is required")
        TransactionType transactionType,

        @NotBlank(message = "Account Number is required")
        long accountNumber,

        @NotBlank(message = "Transaction Amount is required")
        double transactionAmount,


        String description
) {}

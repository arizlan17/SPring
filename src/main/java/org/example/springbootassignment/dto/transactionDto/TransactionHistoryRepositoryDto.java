package org.example.springbootassignment.dto.transactionDto;

import java.util.List;

public record TransactionHistoryRepositoryDto(
        long accountNumber,
        String accountHolderName,
        double currentBalance,
        List<TransactionSummaryDto> lastTransaction
) {
}

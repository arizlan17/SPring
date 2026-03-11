package org.example.springbootassignment.dto.bankAccountDto;

import org.example.springbootassignment.enums.AccountType;
import org.example.springbootassignment.model.BankAccount;

public record BankAccountSummaryDto(
        long accountNumber,
        AccountType accountType,
        double accountBalance
) {
    public static BankAccountSummaryDto from(BankAccount account) {
        return new BankAccountSummaryDto(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountBalance()
        );
    }
}

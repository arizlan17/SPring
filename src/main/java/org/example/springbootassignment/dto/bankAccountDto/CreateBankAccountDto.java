package org.example.springbootassignment.dto.bankAccountDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.springbootassignment.enums.AccountType;

public record CreateBankAccountDto(

        @NotBlank(message = "Customer NIC is required")
        String nic,

        @NotNull(message = "Account balance is required")
        double initialDeposit,

        @NotNull(message = "Account type is required")
        AccountType accountType

//        @NotNull(message = "Account Owner is required")
//        Customer owner

)
{}

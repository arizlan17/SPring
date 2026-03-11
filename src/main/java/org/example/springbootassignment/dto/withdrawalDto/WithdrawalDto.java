package org.example.springbootassignment.dto.withdrawalDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WithdrawalDto(
        @NotBlank(message = "Amount is required")
        @NotNull(message = "Amount is required")
        double amount

) {}

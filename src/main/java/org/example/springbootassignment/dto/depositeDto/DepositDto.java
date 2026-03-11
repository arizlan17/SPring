package org.example.springbootassignment.dto.depositeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepositDto(
        @NotBlank(message = "Amount is required")
        @NotNull(message = "Amount is required")
        double amount
) {
}

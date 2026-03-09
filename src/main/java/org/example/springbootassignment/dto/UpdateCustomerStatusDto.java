package org.example.springbootassignment.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.springbootassignment.validatorInterface.ValidNIC;

public record UpdateCustomerStatusDto(
        @ValidNIC
        @NotBlank(message = "NIC is required")
        String nic,

        boolean isActive

) { }

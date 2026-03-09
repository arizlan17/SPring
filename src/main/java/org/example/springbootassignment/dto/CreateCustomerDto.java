package org.example.springbootassignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.springbootassignment.validatorInterface.ValidNIC;

public record CreateCustomerDto(
        @ValidNIC
        @NotBlank(message = "NIC is required")
        String nic,

        @NotBlank(message = "Customer Name is required")
        String customerName,

        @NotNull(message = "Email Address is required")
        @Email(message = "Invalid Email Address")
        String emailAddress,

        @NotBlank(message = "Phone Number is required")
        @NotNull(message = "Phone Number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
        String phoneNumber
) {
}

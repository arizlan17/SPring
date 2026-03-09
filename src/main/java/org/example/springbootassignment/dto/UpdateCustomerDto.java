package org.example.springbootassignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.springbootassignment.validatorInterface.ValidNIC;

public record UpdateCustomerDto(

        @ValidNIC
        @NotBlank(message = "NIC is required")
        String nic,


        String customerName,

        @Email(message = "Invalid Email Address")
        String emailAddress,

        @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
        String phoneNumber
) {}

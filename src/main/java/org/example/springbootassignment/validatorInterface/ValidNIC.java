package org.example.springbootassignment.validatorInterface;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.springbootassignment.validator.NICValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NICValidator.class )
public @interface ValidNIC {
    String message() default "Invalid NIC format. Must be 9 digits + V/X or 12 digits.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

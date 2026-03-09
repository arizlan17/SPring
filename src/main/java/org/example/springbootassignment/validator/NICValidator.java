package org.example.springbootassignment.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.springbootassignment.validatorInterface.ValidNIC;

public class NICValidator implements ConstraintValidator<ValidNIC,String> {
    @Override
    public boolean isValid(String nic, ConstraintValidatorContext context) {

        if (nic == null || nic.isBlank()){
            return false;
        }
        String oldNicPattern = "^[0-9]{9}[vVxX]$";
        String newNicPattern = "^[0-9]{12}$";
        return nic.matches(oldNicPattern) || nic.matches(newNicPattern);
    }
}

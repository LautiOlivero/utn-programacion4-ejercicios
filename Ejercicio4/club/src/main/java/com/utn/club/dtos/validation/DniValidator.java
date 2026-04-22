package com.utn.club.dtos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<DniValido, String> {

    @Override
    public void initialize(DniValido constraintAnnotation) {}

    @Override
    public boolean isValid(String dniField, ConstraintValidatorContext context) {
        if (dniField == null){
            return false;
        }

        return dniField.matches("^\\d{7,8}$");
    }
}

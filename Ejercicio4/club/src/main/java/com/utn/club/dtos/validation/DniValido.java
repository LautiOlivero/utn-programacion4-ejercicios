package com.utn.club.dtos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DniValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DniValido {
    String message() default "{socio.excepcion.dniexistente}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

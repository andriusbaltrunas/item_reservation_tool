package com.item.reservation.tool.utils.annotation;

import com.item.reservation.tool.utils.validators.ComparePasswordsValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ComparePasswordsValidator.class)
public @interface ComparePasswords {

    // groups and payload must be declared for validation annotation
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "{com.item.reservation.tool.compare.fields}";

    String password();

    String repeatPassword();
}
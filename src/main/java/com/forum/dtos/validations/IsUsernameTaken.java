package com.forum.dtos.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsUsernameTakenValidator.class)
public @interface IsUsernameTaken {
    String message() default "Username already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

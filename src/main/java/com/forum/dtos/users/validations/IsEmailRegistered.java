package com.forum.dtos.users.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsEmailRegisteredValidator.class)
public @interface IsEmailRegistered {
    String message() default "Email already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

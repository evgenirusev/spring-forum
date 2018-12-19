package com.forum.areas.user.models.binding.validations;

import com.forum.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUsernameTakenValidator implements ConstraintValidator<IsUsernameTaken,String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsUsernameTaken isUsernameTaken) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userService.isUsernameTaken(s);
    }
}

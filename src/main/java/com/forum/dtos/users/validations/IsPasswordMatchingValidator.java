package com.forum.dtos.users.validations;

import com.forum.areas.user.models.binding.UserRegisterBindingModel;
import com.forum.dtos.users.UserRegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof UserRegisterBindingModel){
            UserRegisterBindingModel userModel = (UserRegisterBindingModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }
        return false;
    }
}

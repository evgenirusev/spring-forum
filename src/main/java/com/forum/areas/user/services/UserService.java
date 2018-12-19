package com.forum.areas.user.services;

import com.forum.areas.user.models.binding.UserRegisterBindingModel;
import com.forum.areas.user.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserRegisterBindingModel bindingModel);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UserServiceModel findByUsername(String username);
}
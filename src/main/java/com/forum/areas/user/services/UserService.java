package com.forum.areas.user.services;

import com.forum.areas.user.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserServiceModel userServiceModele);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UserServiceModel findByUsername(String username);
}
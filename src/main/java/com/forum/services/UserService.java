package com.forum.services;

import com.forum.dtos.users.UserDto;
import com.forum.dtos.users.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserRegisterDto bindingModel);
}

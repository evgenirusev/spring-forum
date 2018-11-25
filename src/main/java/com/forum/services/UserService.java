package com.forum.services;

import com.forum.dtos.users.UserDto;
import com.forum.dtos.users.UserRegisterDto;

public interface UserService {
    void createUser(UserRegisterDto bindingModel);

    UserDto getUserByUsername(String username);
}

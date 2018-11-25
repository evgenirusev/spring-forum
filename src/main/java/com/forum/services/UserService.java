package com.forum.services;

import com.forum.dtos.users.UserRegisterDto;

public interface UserService {
    void createUser(UserRegisterDto bindingModel);
}

package com.forum.services;

import com.forum.dtos.users.UserRegisterDto;
import com.forum.entities.User;
import com.forum.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(UserRegisterDto userBindingModel) {
        User userEntity = this.modelMapper.map(userBindingModel, User.class);
        this.userRepository.save(userEntity);
    }
}

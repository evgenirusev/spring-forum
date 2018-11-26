package com.forum.services;

import com.forum.dtos.users.UserDto;
import com.forum.dtos.users.UserRegisterDto;
import com.forum.entities.User;
import com.forum.entities.enums.UserRole;
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
        userEntity.setUserRole(UserRole.USER);
        this.userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User userEntity = this.userRepository.getUserByUsername(username);
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = this.modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }
}

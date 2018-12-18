package com.forum.areas.user.services;

import com.forum.dtos.roles.RoleDto;
import com.forum.areas.user.entities.Role;
import com.forum.areas.user.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        RoleDto roleModel = null;
        if (role != null){
            roleModel = this.modelMapper.map(role, RoleDto.class);
        }
        return roleModel;
    }

    @Override
    public void addRole(RoleDto roleDto) {
        Role role = this.modelMapper.map(roleDto, Role.class);
        this.roleRepository.save(role);
    }
}

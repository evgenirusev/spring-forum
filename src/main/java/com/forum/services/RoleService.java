package com.forum.services;

import com.forum.dtos.roles.RoleDto;

public interface RoleService {

    RoleDto findByAuthority(String authority);

    void addRole(RoleDto roleDto);

}

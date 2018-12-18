package com.forum.areas.user.services;

import com.forum.dtos.roles.RoleDto;

public interface RoleService {

    RoleDto findByAuthority(String authority);

    void addRole(RoleDto roleDto);

}

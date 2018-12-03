package com.forum.services;

import com.forum.dtos.roles.RoleModel;

public interface RoleService {
    RoleModel findByAuthority(String authority);
}

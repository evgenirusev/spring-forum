package com.forum.areas.role.services;

import com.forum.areas.role.models.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByAuthority(String authority);

    void addRole(RoleServiceModel roleServiceModel);

}

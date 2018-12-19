package com.forum.areas.roles.services;

import com.forum.areas.roles.models.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByAuthority(String authority);

    void addRole(RoleServiceModel roleServiceModel);

}

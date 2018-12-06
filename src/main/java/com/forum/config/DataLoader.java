package com.forum.config;

import com.forum.dtos.roles.RoleDto;
import com.forum.services.CategoryService;
import com.forum.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleService roleService;

    private final CategoryService categoryService;

    @Autowired
    public DataLoader(RoleService roleService, CategoryService categoryService) {
        this.roleService = roleService;
        this.categoryService = categoryService;
    }

    public void run(ApplicationArguments args) {
        RoleDto userRole = this.roleService.findByAuthority("USER");
        RoleDto adminRole = this.roleService.findByAuthority("ADMIN");

        if (userRole == null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setAuthority("USER");
            this.roleService.addRole(roleDto);
        }

        if (adminRole == null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setAuthority("ADMIN");
            this.roleService.addRole(roleDto);
        }
    }
}
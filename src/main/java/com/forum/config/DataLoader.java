package com.forum.config;

import com.forum.cache.DataCacheSingleton;
import com.forum.areas.category.models.view.CategoryNamesViewModel;
import com.forum.areas.role.models.service.RoleServiceModel;
import com.forum.areas.category.services.CategoryService;
import com.forum.areas.role.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        RoleServiceModel userRole = this.roleService.findByAuthority("USER");
        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");

        if (userRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("USER");
            this.roleService.addRole(roleServiceModel);
        }

        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        List<CategoryNamesViewModel> categoryDtos = new ArrayList<>();
        this.categoryService.findAllCategories().forEach(category -> {
            CategoryNamesViewModel categoryNamesViewModel = new CategoryNamesViewModel();
            categoryNamesViewModel.setName(category.getName());
            categoryDtos.add(categoryNamesViewModel);
        });
        DataCacheSingleton.getInstance().addCategories(categoryDtos);
    }
}
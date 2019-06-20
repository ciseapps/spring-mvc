package com.cise.cms.core;

import com.cise.cms.core.applications.configuration.FileStorageProperties;
import com.cise.cms.core.modules.menu.service.MenuService;
import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CmsCoreApplication implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(CmsCoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // default role
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(1, "Super Admin"));
        roleList.add(new Role(2, "Procurement"));
        roleList.add(new Role(3, "Kadep Procurement"));
        roleList.add(new Role(4, "Kadep Requester"));
        roleList.add(new Role(5, "Requester"));
        for (Role role : roleList) {
            Role tmp = roleService.findByRoleName(role.getRoleName());
            if (tmp == null) roleService.createOrUpdate(role);
        }
        // default menu
    }
}

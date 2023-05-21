package com.project.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.project.enums.RoleEnum;
import com.project.repo.RoleRepo;

@Singleton
@Startup
public class Init {
    @Inject
    private RoleRepo roleRepo;

    @PostConstruct
    public void load() {
        for (RoleEnum r : RoleEnum.values())
            roleRepo.createRole(r.toString());
    }
}

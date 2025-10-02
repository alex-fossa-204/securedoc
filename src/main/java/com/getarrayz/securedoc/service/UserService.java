package com.getarrayz.securedoc.service;

import com.getarrayz.securedoc.entity.RoleEntity;

public interface UserService {

    void createUser(
            String firstName,
            String lastName,
            String email,
            String password
    );

    RoleEntity getRoleName(String name);

}

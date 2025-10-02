package com.getarrayz.securedoc.service;

public interface UserService {

    void createUser(
            String firstName,
            String lastName,
            String email,
            String password
    );

}

package com.getarrayz.securedoc.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {

    @Async
    void sendNewAccountEmail(String name, String to, String token);

    @Async
    void sendPasswordResetEmail(String name, String to, String token);

}

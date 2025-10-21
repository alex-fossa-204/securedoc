package com.getarrayz.securedoc;

import com.getarrayz.securedoc.domain.RequestContext;
import com.getarrayz.securedoc.entity.RoleEntity;
import com.getarrayz.securedoc.enumeration.Authority;
import com.getarrayz.securedoc.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecureDocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureDocApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            RequestContext.setUserId(0L);
            var userRole = new RoleEntity();
            userRole.setName(Authority.USER.name());
            userRole.setAuthorities(Authority.USER);

            var adminRole = new RoleEntity();
            userRole.setName(Authority.ADMIN.name());
            userRole.setAuthorities(Authority.ADMIN);
        };
    }

}

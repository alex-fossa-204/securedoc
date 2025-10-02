package com.getarrayz.securedoc.service.impl;

import com.getarrayz.securedoc.entity.RoleEntity;
import com.getarrayz.securedoc.enumeration.Authority;
import com.getarrayz.securedoc.enumeration.EventType;
import com.getarrayz.securedoc.event.UserEvent;
import com.getarrayz.securedoc.repository.ConfirmationRepository;
import com.getarrayz.securedoc.repository.CredentialRepository;
import com.getarrayz.securedoc.repository.RoleRepository;
import com.getarrayz.securedoc.repository.UserRepository;
import com.getarrayz.securedoc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.getarrayz.securedoc.utils.UserUtils.createConfirmationEntity;
import static com.getarrayz.securedoc.utils.UserUtils.createCredentialEntity;
import static com.getarrayz.securedoc.utils.UserUtils.createNewUser;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CredentialRepository credentialRepository;

    private final ConfirmationRepository confirmationRepository;

    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    @Override
    public void createUser(String firstName, String lastName, String email, String password) {

        final var userEntity = userRepository.save(
                createNewUser(firstName,
                        lastName,
                        email,
                        password,
                        getDefaultRole()
                )
        );

        final var credentialEntity = credentialRepository.save(
                createCredentialEntity(
                        userEntity,
                        password
                )
        );

        final var confirmationEntity = createConfirmationEntity(userEntity);

        applicationEventPublisher.publishEvent(new UserEvent(
                userEntity,
                EventType.REGISTRATION,
                Map.of("key", confirmationEntity.getKey())
        ));

    }

    @Override
    public RoleEntity getRoleName(String name) {
        return Optional.ofNullable(name)
                .flatMap(roleRepository::findByNameIgnoreCase)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private RoleEntity getDefaultRole() {
        return Optional.of(Authority.USER.name())
                .flatMap(roleRepository::findByNameIgnoreCase)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

}

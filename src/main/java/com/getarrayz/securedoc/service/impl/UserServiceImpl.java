package com.getarrayz.securedoc.service.impl;

import com.getarrayz.securedoc.entity.ConfirmationEntity;
import com.getarrayz.securedoc.entity.CredentialEntity;
import com.getarrayz.securedoc.entity.UserEntity;
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

import static com.getarrayz.securedoc.service.impl.UserServiceImpl.UserServiceHelper.createConfirmationEntity;
import static com.getarrayz.securedoc.service.impl.UserServiceImpl.UserServiceHelper.createCredentialEntity;
import static com.getarrayz.securedoc.service.impl.UserServiceImpl.UserServiceHelper.createNewUser;

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
                        password
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

    static class UserServiceHelper {

        static UserEntity createNewUser(
                String firstName,
                String lastName,
                String email,
                String password
        ) {
            return UserEntity.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .build();
        }

        static CredentialEntity createCredentialEntity(
                UserEntity userEntity,
                String password
        ) {
            return CredentialEntity.builder()
                    .userEntity(userEntity)
                    .password(password)
                    .build();
        }

        static ConfirmationEntity createConfirmationEntity(UserEntity userEntity) {
            return ConfirmationEntity.builder()
                    .userEntity(userEntity)
                    .build();
        }

    }

}

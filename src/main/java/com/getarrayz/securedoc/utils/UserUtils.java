package com.getarrayz.securedoc.utils;

import com.getarrayz.securedoc.entity.ConfirmationEntity;
import com.getarrayz.securedoc.entity.CredentialEntity;
import com.getarrayz.securedoc.entity.RoleEntity;
import com.getarrayz.securedoc.entity.UserEntity;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserUtils {

    public static UserEntity createNewUser(
            String firstName,
            String lastName,
            String email,
            String password,
            RoleEntity role
    ) {
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .lastLogin(LocalDateTime.now())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(false)
                .loginAttempts(0)
                .qrCodeSecret(StringUtils.EMPTY)
                .phone(StringUtils.EMPTY)
                .bio(StringUtils.EMPTY)
                .imageUrl("https://cdn-icons-png.flaticon.com/512/149/149071.png")
                .role(role)
                .build();
    }

    public static CredentialEntity createCredentialEntity(
            UserEntity userEntity,
            String password
    ) {
        return CredentialEntity.builder()
                .userEntity(userEntity)
                .password(password)
                .build();
    }

    public static ConfirmationEntity createConfirmationEntity(UserEntity userEntity) {
        return ConfirmationEntity.builder()
                .userEntity(userEntity)
                .build();
    }

}

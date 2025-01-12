package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
public class UserEntity extends Auditable {

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer loginAttempts;

    private LocalDateTime lastLogin;

    private String phone;

    private String bio;

    private String imageUrl;

    private Boolean accountNoExpired = Boolean.FALSE;

    private Boolean accountNonLocked = Boolean.FALSE;

    private Boolean enabled = Boolean.FALSE;

    private Boolean mfa = Boolean.FALSE;

    @JsonIgnore
    private String qrCodeSecret;

    @Column(columnDefinition = "TEXT")
    private String qrCodeImageUri;

    private String roles; //todo make a class
}

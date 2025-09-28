package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
public class UserEntity extends Auditable {

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private Integer loginAttempts;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private String phone;

    @Column
    private String bio;

    @Column
    private String imageUrl;

    @Column
    private Boolean accountNoExpired = Boolean.FALSE;

    @Column
    private Boolean accountNonLocked = Boolean.FALSE;

    @Column
    private Boolean enabled = Boolean.FALSE;

    @Column
    private Boolean mfa = Boolean.FALSE;

    @JsonIgnore
    private String qrCodeSecret;

    @Column(columnDefinition = "TEXT")
    private String qrCodeImageUri;

    @ManyToOne(fetch = FetchType.EAGER) //todo сделать entity-graph
    @JoinTable(name = "roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private RoleEntity roles;
}

package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "roles")
@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
public class RoleEntity extends Auditable {

    private String name;

    private String authorities;

}

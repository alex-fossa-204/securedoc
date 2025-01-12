package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.getarrayz.securedoc.domain.RequestContext;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;


@EntityListeners(value = {
        AuditingEntityListener.class
})
@JsonIgnoreProperties(
        value = {
                "createdAt",
                "updatedAt"
        },
        allowGetters = true
)
@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

    @Id
    @Column(name = "id", updatable = false)
    @NotNull
    @SequenceGenerators(value = {
            @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    private Long id;

    @Column(name = "reference_id")
    @NotNull
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();

    @Column(name = "created_by", nullable = false, updatable = false)
    @NotNull
    private Long createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    @NotNull
    private Long updatedBy;

    @Column(name = "updated_at", nullable = false)
    @CreatedDate
    @NotNull
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        final var userId = RequestContext.getUserId();

        Optional.ofNullable(userId).ifPresentOrElse(
                id -> {
                    this.setCreatedBy(id);
                    this.setCreatedAt(LocalDateTime.now());
                    this.setUpdatedBy(id);
                    this.setUpdatedAt(LocalDateTime.now());
                },
                () -> {
                    throw new RuntimeException("Не удалось сохранить сущность без уникального userId пользователя");
                }
        );
    }

    @PreUpdate
    public void beforeUpdate() {
        final var userId = RequestContext.getUserId();

        Optional.ofNullable(userId).ifPresentOrElse(
                id -> {
                    this.setUpdatedBy(id);
                    this.setUpdatedAt(LocalDateTime.now());
                },
                () -> {
                    throw new RuntimeException("Не удалось обновить сущность без уникального userId пользователя");
                }
        );
    }

}

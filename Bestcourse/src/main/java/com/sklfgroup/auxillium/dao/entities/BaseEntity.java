package com.sklfgroup.auxillium.dao.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    protected String uuid = UUID.randomUUID().toString();

    @Column(name = "deleted")
    protected boolean deleted = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    protected ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected ZonedDateTime updatedAt;
}

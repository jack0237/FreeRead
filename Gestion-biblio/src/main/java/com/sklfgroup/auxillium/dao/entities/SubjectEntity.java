package com.sklfgroup.auxillium.dao.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Subject")
public class SubjectEntity extends BaseEntity {

    @Column( name = "Subject", length = 100)
    private String nameSubject;

    @Column( name = "state")
    private boolean active = true;

}

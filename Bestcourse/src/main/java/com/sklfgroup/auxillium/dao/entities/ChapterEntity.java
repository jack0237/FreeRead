package com.sklfgroup.auxillium.dao.entities;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Chapter")
public class ChapterEntity extends BaseEntity {

    @Column(name = "nameChapter", nullable = false)
    private String nameChapter;

    @Column( name = "state")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "Subject_idSubject")
    private SubjectEntity subject;


}


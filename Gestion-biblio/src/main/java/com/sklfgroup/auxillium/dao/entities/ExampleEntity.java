package com.sklfgroup.auxillium.dao.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Example")
public class ExampleEntity extends BaseEntity {

    @Column(name = "contentExample", nullable = false)
    private String Example;

    @Column( name = "state")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "lesson_idLesson")
    private LessonEntity lesson;

}

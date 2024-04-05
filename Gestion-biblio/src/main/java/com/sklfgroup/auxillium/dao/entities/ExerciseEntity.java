package com.sklfgroup.auxillium.dao.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Exercise")
public class ExerciseEntity extends BaseEntity{

    @Column(name = "contentExercise", nullable = false)
    private String Exercise;

    @Column( name = "state")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "lesson_idLesson")
    private LessonEntity lessonEntity;


}

package com.sklfgroup.auxillium.dao.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Lesson")
public class LessonEntity extends BaseEntity{

    @Column( name = "titleLesson", length = 45)
    private String titleLesson;

    @Column( name = "contentLesson", length = 100)
    private String contentLesson;

    @Column( name = "state")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "Chapter_idChapter")
    private ChapterEntity chapter;
}

package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;

public interface LessonService {
    LessonEntity save (LessonEntity entity) throws AlreadyExistsException;
    Object delete(String lessonId);
    Object update(String lessonId, String LessonName, String CLesson);
    Object find(String nameLesson);
    LessonEntity activate(String subjectId);
    LessonEntity deactivate(String subjectId);
}

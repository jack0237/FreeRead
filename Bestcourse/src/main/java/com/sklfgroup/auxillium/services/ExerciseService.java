package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;

public interface ExerciseService {
    ExerciseEntity save (ExerciseEntity entity) throws AlreadyExistsException;
    Object delete(String exerciseId);
    Object update(String exerciseId, String Exercise);
    Object find(String Exercise);
    ExerciseEntity activate(String subjectId);
    ExerciseEntity deactivate(String subjectId);
}

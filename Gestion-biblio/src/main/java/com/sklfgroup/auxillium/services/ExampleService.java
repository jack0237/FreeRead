package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;

public interface ExampleService {
    ExampleEntity save(ExampleEntity entity) throws AlreadyExistsException;
    Object delete(String exampleId);
    Object update(String exampleId, String Example);
    Object find(String Example);
    ExampleEntity activate(String exampleId);
    ExampleEntity deactivate(String exampleId);
}

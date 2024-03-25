package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import com.sklfgroup.auxillium.dao.repositories.ExampleRepository;
import com.sklfgroup.auxillium.dao.repositories.ExerciseRepository;
import com.sklfgroup.auxillium.dao.repositories.LessonRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.helpers.Pager;
import com.sklfgroup.auxillium.services.ExampleService;
import com.sklfgroup.auxillium.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class IExerciseService implements ExerciseService {

    private final LessonRepository lessonRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(IExerciseService.class);
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseEntity save(ExerciseEntity entity) throws AlreadyExistsException {
        Optional<ExerciseEntity> existingExercise = exerciseRepository.findByDeletedFalseAndUuid(entity.getExercise());
        if (existingExercise.isPresent()){
            LOGGER.error("is it a second part ? ");
            throw new AlreadyExistsException(" already exists");
        }
        entity.setLessonEntity(lessonRepository.findByDeletedFalseAndUuid(entity.getLessonEntity().getUuid()).orElseThrow());
        ExerciseEntity E = exerciseRepository.save(entity);

        return E;
    }

    @Override
    public ExerciseEntity delete(String exerciseId) {
        LOGGER.info("delete : {}", exerciseId);
        ExerciseEntity entity = exerciseRepository.findByDeletedFalseAndUuid(exerciseId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + exerciseId));
        entity.setDeleted(true);
        return exerciseRepository.save(entity);
    }

    @Override
    public ExerciseEntity update(String exerciseId, String Exercise ) {
        LOGGER.info("update subject with id : {} and data ; {}",  exerciseId, Exercise);
        ExerciseEntity entity = exerciseRepository.findByDeletedFalseAndUuid(exerciseId).orElseThrow();
        entity.setExercise(Exercise);
        return exerciseRepository.save(entity);

    }

    @Override
    public Object find(String Exercise) {
        LOGGER.info("find Chapter with name : {}", Exercise);
        return exerciseRepository.findByDeletedFalseAndUuid(Exercise).orElseThrow(
                () -> new NotFoundException("Exercise with content: " + Exercise + "not found"));

    }

    @Override
    public ExerciseEntity activate(String lessonId) {
        ExerciseEntity exerciseentity = exerciseRepository.findByDeletedFalseAndUuid(lessonId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        exerciseentity.setActive(true);
        return exerciseRepository.save(exerciseentity);
    }

    @Override
    public ExerciseEntity deactivate(String lessonId) {
        ExerciseEntity exerciseentity = exerciseRepository.findByDeletedFalseAndUuid(lessonId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        exerciseentity.setActive(false);
        return exerciseRepository.save(exerciseentity);
    }


}

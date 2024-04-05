package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.repositories.ExampleRepository;
import com.sklfgroup.auxillium.dao.repositories.LessonRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.services.ExampleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class IExampleService implements ExampleService {

    private final LessonRepository lessonRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(IExampleService.class);
    private final ExampleRepository exampleRepository;

    @Override
    public ExampleEntity save(ExampleEntity entity) throws AlreadyExistsException {
        Optional<ExampleEntity> existingExample = exampleRepository.findByDeletedFalseAndUuid(entity.getExample());
        if (existingExample.isPresent()){
            LOGGER.error("is it a second part ? ");
            throw new AlreadyExistsException(" already exists");
        }
        entity.setLesson(lessonRepository.findByDeletedFalseAndUuid(entity.getLesson().getUuid()).orElseThrow());
        ExampleEntity E = exampleRepository.save(entity);

        return E;
    }

    @Override
    public ExampleEntity delete(String exampleId) {
        LOGGER.info("delete : {}", exampleId);
        ExampleEntity entity = exampleRepository.findByDeletedFalseAndUuid(exampleId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + exampleId));
        entity.setDeleted(true);
        return exampleRepository.save(entity);
    }

    @Override
    public ExampleEntity update(String exampleId, String Example ) {
        LOGGER.info("update subject with id : {} and data ; {}",  exampleId, Example);
        ExampleEntity entity = exampleRepository.findByDeletedFalseAndUuid(exampleId).orElseThrow();
        entity.setExample(Example);
        return exampleRepository.save(entity);

    }

    @Override
    public Object find(String Example) {
        LOGGER.info("find Chapter with name : {}", Example);
        return exampleRepository.findByDeletedFalseAndUuid(Example).orElseThrow(
                () -> new NotFoundException("Example with content: " + Example + "not found"));

    }

    @Override
    public ExampleEntity activate(String exampleId) {
        ExampleEntity entity = exampleRepository.findByDeletedFalseAndUuid(exampleId).orElseThrow(() ->
                new NotFoundException("Classe introuvable"));
        entity.setActive(true);
        return exampleRepository.save(entity);
    }

    @Override
    public ExampleEntity deactivate(String exampleId) {
        ExampleEntity entity = exampleRepository.findByDeletedFalseAndUuid(exampleId).orElseThrow(() ->
                new NotFoundException("Classe introuvable"));
        entity.setActive(false);
        return exampleRepository.save(entity);
    }

}

package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.dao.repositories.ChapterRepository;
import com.sklfgroup.auxillium.dao.repositories.LessonRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ILessonServiceImpl implements LessonService {

    private final ChapterRepository chapterRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ILessonServiceImpl.class);
    private final LessonRepository lessonRepository;

    @Override
    public LessonEntity save(LessonEntity entity) throws AlreadyExistsException {
        Optional<LessonEntity> existingLesson = lessonRepository.findByDeletedFalseAndTitleLesson(entity.getContentLesson());
        if (existingLesson.isPresent()){
            LOGGER.error("This lesson already exist do you want to put a second part ?");
            throw new AlreadyExistsException("already exist");
        }
        entity.setChapter(chapterRepository.findByDeletedFalseAndUuid(entity.getChapter().getUuid()).orElseThrow());

        return lessonRepository.save(entity);
    }

    @Override
    public LessonEntity delete(String lessonId) {
        LOGGER.info("delete : {}", lessonId);
        LessonEntity entity = lessonRepository.findByDeletedFalseAndUuid(lessonId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + lessonId));
        entity.setDeleted(true);
        return lessonRepository.save(entity);
    }

    public LessonEntity update(String lessonId, String LessonName, String CLesson ) {
        LOGGER.info("update subject with id : {} and data ; {} and content {};",  lessonId, LessonName, CLesson);
        LessonEntity entity = lessonRepository.findByDeletedFalseAndUuid(lessonId). orElseThrow();
        entity.setTitleLesson(LessonName);
        entity.setContentLesson(CLesson);
        return lessonRepository.save(entity);
    }

    @Override
    public Object find(String nameLesson) {
        LOGGER.info("find Chapter with name : {}", nameLesson);
        return lessonRepository.findByDeletedFalseAndTitleLesson(nameLesson).orElseThrow(
                () -> new NotFoundException("lesson with tittle: " + nameLesson + "not found"));


    }

    @Override
    public LessonEntity activate(String lessonId) {
        LessonEntity lessonentity = lessonRepository.findByDeletedFalseAndUuid(lessonId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        lessonentity.setActive(true);
        return lessonRepository.save(lessonentity);
    }

    @Override
    public LessonEntity deactivate(String lessonId) {
        LessonEntity lessonentity = lessonRepository.findByDeletedFalseAndUuid(lessonId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        lessonentity.setActive(false);
        return lessonRepository.save(lessonentity);
    }

}

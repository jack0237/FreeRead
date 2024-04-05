package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.dao.repositories.ChapterRepository;
import com.sklfgroup.auxillium.dao.repositories.SubjectRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IChapterService implements ChapterService {
    private final SubjectRepository subjectRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ISubjectService.class);
    private final ChapterRepository chapterRepository;

    @Override

    public ChapterEntity save(ChapterEntity entity) throws AlreadyExistsException {
        Optional<ChapterEntity> existingSpecialty = chapterRepository.findByDeletedFalseAndNameChapter(entity.getNameChapter());
        if (existingSpecialty.isPresent()){
            LOGGER.error("field already exists");
            throw new AlreadyExistsException("specialty already exists");
        }
        entity.setSubject(subjectRepository.findByDeletedFalseAndUuid(entity.getSubject().getUuid()).orElseThrow());
        return chapterRepository.save(entity);
    }

    @Override
    public ChapterEntity delete(String chapterId) {
        LOGGER.info("delete : {}", chapterId);
        ChapterEntity entity = chapterRepository.findByDeletedFalseAndUuid(chapterId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + chapterId));
        entity.setDeleted(true);
        return chapterRepository.save(entity);
    }

    @Override
    public ChapterEntity update(String chapterId, String nameChapter ) {
        LOGGER.info("update subject with id : {} and data ; {}",  chapterId, nameChapter);
        ChapterEntity entity = chapterRepository.findByDeletedFalseAndUuid(chapterId).orElseThrow();
        entity.setNameChapter(nameChapter);
        return chapterRepository.save(entity);
    }

    @Override
    public Object find(String nameChapter) {
        LOGGER.info("find Chapter with name : {}", nameChapter);
        return chapterRepository.findByDeletedFalseAndNameChapter(nameChapter).orElseThrow(
        () -> new NotFoundException("subject with name: " + nameChapter + "not found"));


    }
    @Override
    public ChapterEntity activate(String chapterId) {
        ChapterEntity chapterEntity = chapterRepository.findByDeletedFalseAndUuid(chapterId).orElseThrow(() ->
                new NotFoundException("Classe introuvable"));
        chapterEntity.setActive(true);
        return chapterRepository.save(chapterEntity);
    }

    @Override
    public ChapterEntity deactivate(String chapterId) {
        ChapterEntity chapterEntity = chapterRepository.findByDeletedFalseAndUuid(chapterId).orElseThrow(() ->
                new NotFoundException("Classe introuvable"));
        chapterEntity.setActive(false);
        return chapterRepository.save(chapterEntity);
    }


}

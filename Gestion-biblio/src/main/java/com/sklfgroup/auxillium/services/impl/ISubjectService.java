package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.dao.repositories.SubjectRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.helpers.ObjectHelper;
import com.sklfgroup.auxillium.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ISubjectService implements SubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ISubjectService.class);
    private final SubjectRepository subjectRepository;

    @Override
    public SubjectEntity save(SubjectEntity entity) throws AlreadyExistsException {

        Optional<SubjectEntity> existingField = subjectRepository.findByDeletedFalseAndNameSubject(entity.getNameSubject());
        if (existingField.isPresent()) {
            LOGGER.error("field already exists");
            throw new AlreadyExistsException("field already exists");
        }
        return subjectRepository.save(entity);
    }
    @Override
    public SubjectEntity delete(String subjectId) {
        LOGGER.info("delete : {}", subjectId);
        SubjectEntity entity = subjectRepository.findByDeletedFalseAndUuid(subjectId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + subjectId));
        entity.setDeleted(true);
        return subjectRepository.save(entity);
    }

    @Override
    public SubjectEntity update(String subjectId, String nameSubject ) {
        LOGGER.info("update subject with id : {} and data ; {}",  subjectId, nameSubject);
        SubjectEntity subjectEntity = subjectRepository.findByDeletedFalseAndUuid(subjectId).orElseThrow();
        subjectEntity.setNameSubject(nameSubject);
        return subjectRepository.save(subjectEntity);
    }


    @Override
    public SubjectEntity findByName(String nameSubject) {
        LOGGER.info("find subject with name : {}", nameSubject);
        return subjectRepository.findByDeletedFalseAndNameSubject(nameSubject).orElseThrow(
                () -> new NotFoundException("subject with name: " + nameSubject + "not found"));

    }

    @Override
    public List<SubjectEntity> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public SubjectEntity activate(String subjectId) {
        SubjectEntity subjectEntity = subjectRepository.findByDeletedFalseAndUuid(subjectId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        subjectEntity.setActive(true);
        return subjectRepository.save(subjectEntity);
    }

    @Override
    public SubjectEntity deactivate(String subjectId) {
        SubjectEntity subjectEntity = subjectRepository.findByDeletedFalseAndUuid(subjectId).orElseThrow(() -> new NotFoundException("Classe introuvable"));
        subjectEntity.setActive(false);
        return subjectRepository.save(subjectEntity);
    }

}

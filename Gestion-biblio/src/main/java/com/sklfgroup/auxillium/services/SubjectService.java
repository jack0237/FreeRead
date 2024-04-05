package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    SubjectEntity save (SubjectEntity entity) throws AlreadyExistsException;
    SubjectEntity delete(String subjectId);
    SubjectEntity update(String subjectId, String nameSubject);
    SubjectEntity findByName(String nameSubject);

    List<SubjectEntity> findAll();
    SubjectEntity activate(String subjectId);
    SubjectEntity deactivate(String subjectId);

}

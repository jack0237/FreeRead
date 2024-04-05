package com.sklfgroup.auxillium.rest.controllers;


import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.rest.api.SubjectApi;
import com.sklfgroup.auxillium.rest.dto.requests.subject.SubjectCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectResponse;
import com.sklfgroup.auxillium.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class CategorieController implements SubjectApi {
    private final SubjectService subjectService;

    @Override
    public ResponseEntity<SubjectResponse> createSubject(SubjectCreateRequest request) throws AlreadyExistsException {
        return ResponseEntity.ok(new SubjectResponse(subjectService.save(request.toEntity())));
    }

    @Override
    public ResponseEntity<SubjectResponse> deleteSubject(String subjectId) {
        return ResponseEntity.ok(new SubjectResponse(subjectService.delete(subjectId)));
    }

    @Override
    public ResponseEntity<SubjectResponse> listSubject(String subjectId) {
        return null;
    }

    @Override
    public List<SubjectEntity> findAll() {
        return subjectService.findAll();
    }


    @Override
    public ResponseEntity<SubjectResponse> updateSubject( String subjectId, String nameSubject) {
        return ResponseEntity.ok(new SubjectResponse(subjectService.update(subjectId, nameSubject)));
    }

    @Override
    public ResponseEntity<SubjectResponse> findSubject(String nameSubject) {
      return ResponseEntity.ok(new SubjectResponse(subjectService.findByName(nameSubject)));

    }


    @Override
    public ResponseEntity<SubjectResponse> activate(String subjectId) throws NotFoundException {
       return ResponseEntity.ok(new SubjectResponse(subjectService.activate(subjectId)));
    }

    @Override
    public ResponseEntity<SubjectResponse> deactivate(String subjectId) {
        return ResponseEntity.ok(new SubjectResponse(subjectService.deactivate(subjectId)));
    }


}

package com.sklfgroup.auxillium.rest.controllers;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.rest.api.ExampleApi;
import com.sklfgroup.auxillium.rest.dto.requests.example.ExampleCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.rest.dto.responses.example.ExampleResponse;
import com.sklfgroup.auxillium.services.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ExampleController implements ExampleApi {

    private final ExampleService exampleService;

    @Override
    public ResponseEntity<ExampleResponse> createExample(ExampleCreateRequest request) throws AlreadyExistsException {
        ExampleResponse e = new ExampleResponse(exampleService.save(request.toEntity()));
        return ResponseEntity.ok(e);
    }

    @Override
    public ResponseEntity<ExampleResponse> deleteExample(String exampleId) {
       return ResponseEntity.ok(new ExampleResponse((ExampleEntity) exampleService.delete(exampleId)));

    }
    @Override
    public ResponseEntity<ExampleResponse> updateExample(String exampleId, String Example) {
        return ResponseEntity.ok(new ExampleResponse((ExampleEntity) exampleService.update(exampleId, Example)));
    }

    @Override
    public ResponseEntity<ExampleResponse> findExample(String Example) {
     return ResponseEntity.ok(new ExampleResponse((ExampleEntity) exampleService.find(Example)));

    }

    @Override
    public ResponseEntity<ExampleResponse> activate(String exampleId) throws NotFoundException {
        return ResponseEntity.ok(new ExampleResponse((Page<ExampleEntity>) exampleService.activate(exampleId)));

    }

    @Override
    public ResponseEntity<ExampleResponse> deactivate(String exampleId) {
        return ResponseEntity.ok(new ExampleResponse((Page<ExampleEntity>) exampleService.deactivate(exampleId)));
    }
}

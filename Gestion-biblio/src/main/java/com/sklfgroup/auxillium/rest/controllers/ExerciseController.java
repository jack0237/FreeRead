package com.sklfgroup.auxillium.rest.controllers;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.rest.api.ExampleApi;
import com.sklfgroup.auxillium.rest.api.ExerciseApi;
import com.sklfgroup.auxillium.rest.dto.requests.example.ExampleCreateRequest;
import com.sklfgroup.auxillium.rest.dto.requests.exercise.ExerciseCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.example.ExampleResponse;
import com.sklfgroup.auxillium.rest.dto.responses.exercise.ExerciseResponse;
import com.sklfgroup.auxillium.services.ExampleService;
import com.sklfgroup.auxillium.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ExerciseController implements ExerciseApi {

    private final ExerciseService exerciseService;

    @Override
    public ResponseEntity<ExerciseResponse> createExercise(ExerciseCreateRequest request) throws AlreadyExistsException {
        ExerciseResponse e = new ExerciseResponse(exerciseService.save(request.toEntity()));
        return ResponseEntity.ok(e);
    }


    @Override
    public ResponseEntity<ExerciseResponse> deleteExercise(String exerciseId) {
        return ResponseEntity.ok(new ExerciseResponse(exerciseService.delete(exerciseId)));

    }
    @Override
    public ResponseEntity<ExerciseResponse> updateExercise(String exerciseId, String Exercise) {
        return ResponseEntity.ok(new ExerciseResponse( exerciseService.update(exerciseId, Exercise)));
    }

    @Override
    public ResponseEntity<ExerciseResponse> findExercise(String Exercise) {
        return ResponseEntity.ok(new ExerciseResponse( exerciseService.find(Exercise)));

    }

    @Override
    public ResponseEntity<ExerciseResponse> activate(String exerciseId) throws NotFoundException {
        return ResponseEntity.ok(new ExerciseResponse( exerciseService.activate(exerciseId)));

    }

    @Override
    public ResponseEntity<ExerciseResponse> deactivate(String exerciseId) {
        return ResponseEntity.ok(new ExerciseResponse( exerciseService.deactivate(exerciseId)));
    }


}

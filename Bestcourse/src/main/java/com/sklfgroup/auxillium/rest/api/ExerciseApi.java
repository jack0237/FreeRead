package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.rest.dto.requests.chapter.ChapterCreateRequest;
import com.sklfgroup.auxillium.rest.dto.requests.exercise.ExerciseCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.rest.dto.responses.example.ExampleResponse;
import com.sklfgroup.auxillium.rest.dto.responses.exercise.ExerciseResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/registration/exercise", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ExerciseApi {


        @PostMapping
        ResponseEntity<ExerciseResponse> createExercise(@Valid @RequestBody ExerciseCreateRequest requestDto)
                throws AlreadyExistsException;

        @DeleteMapping("{id}")
        ResponseEntity<ExerciseResponse> deleteExercise(@PathVariable( name = "id") String id) ;

        @PutMapping("{id}")
        ResponseEntity<ExerciseResponse> updateExercise(@PathVariable( name = "id") String id,
                                                      @RequestParam( "Exercise") String Exercise) ;

        @GetMapping
        ResponseEntity<ExerciseResponse> findExercise(@RequestParam("Exercise") String Exercise);

        @PutMapping("/activate/{id}")
        ResponseEntity<ExerciseResponse> activate(@PathVariable( name = "id") String id);


        @PutMapping("/deactivate/{id}")
        ResponseEntity<ExerciseResponse> deactivate(@PathVariable(name = "id") String id);
    }

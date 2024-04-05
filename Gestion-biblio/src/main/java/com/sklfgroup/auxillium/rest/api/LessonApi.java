package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.rest.dto.requests.lesson.LessonCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.rest.dto.responses.exercise.ExerciseResponse;
import com.sklfgroup.auxillium.rest.dto.responses.lesson.LessonResponse;
import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/Ouvrage", produces = MediaType.APPLICATION_JSON_VALUE)
public interface LessonApi {
    @PostMapping
    ResponseEntity<LessonResponse> createLesson(@Valid @RequestBody LessonCreateRequest requestDto) throws AlreadyExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<LessonResponse> deleteLesson(@PathVariable( name = "id") String id) ;

    @PutMapping("update/{id}")
    ResponseEntity<LessonResponse> updateLesson(@PathVariable( name = "id") String id,
                                                @RequestParam String LessonName, String CLesson );

    @GetMapping
    ResponseEntity<LessonResponse> findLesson(@RequestParam("Lesson Name") String nameLesson);

    @PutMapping("/activate/{id}")
    ResponseEntity<LessonResponse> activate(@PathVariable( name = "id") String id);


    @PutMapping("/deactivate/{id}")
    ResponseEntity<LessonResponse> deactivate(@PathVariable(name = "id") String id);

}

package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.rest.dto.requests.chapter.ChapterCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/Compte", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChapterApi {
    @PostMapping
    ResponseEntity<ChapterResponse> createChapter(@Valid @RequestBody ChapterCreateRequest requestDto) throws AlreadyExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<ChapterResponse> deleteChapter(@PathVariable( name = "id") String id) ;

    @PutMapping("{id}")
    ResponseEntity<ChapterResponse> updateChapter(@PathVariable( name = "id") String id,
                                                  @RequestParam( "nameChapter") String nameChapter) ;

    @GetMapping
    ResponseEntity<ChapterResponse> findChapter(@RequestParam("nameChapter") String nameChapter);

    @PutMapping("/activate/{id}")
    ResponseEntity<ChapterResponse> activate(@PathVariable( name = "id") String id);


    @PutMapping("/deactivate/{id}")
    ResponseEntity<ChapterResponse> deactivate(@PathVariable(name = "id") String id);

}

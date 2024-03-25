package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.rest.dto.requests.subject.SubjectCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/Client", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SubjectApi {
    @PostMapping
    ResponseEntity<SubjectResponse> createSubject(@Valid @RequestBody SubjectCreateRequest requestDto)
            throws AlreadyExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<SubjectResponse> deleteSubject(@PathVariable( name = "id") String id) ;

    @PutMapping("{id}")
    ResponseEntity<SubjectResponse> updateSubject(@PathVariable( name = "id") String id,
                                                  @RequestParam( "nameSubject") String nameSubject) ;
    @GetMapping
    ResponseEntity<SubjectResponse> findSubject(@RequestParam("nameSubject") String nameSubject);

    @PutMapping("/activate/{id}")
    ResponseEntity<SubjectResponse> activate(@PathVariable( name = "id") String id);


    @PutMapping("/deactivate/{id}")
    ResponseEntity<SubjectResponse> deactivate(@PathVariable(name = "id") String id);

}

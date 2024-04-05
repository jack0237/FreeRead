package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.rest.dto.requests.chapter.ChapterCreateRequest;
import com.sklfgroup.auxillium.rest.dto.requests.example.ExampleCreateRequest;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterResponse;
import com.sklfgroup.auxillium.rest.dto.responses.example.ExampleResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping(value = "/Caisier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public interface ExampleApi {

        @PostMapping
        ResponseEntity<ExampleResponse> createExample(@Valid @RequestBody ExampleCreateRequest requestDto)
                throws AlreadyExistsException;

        @DeleteMapping("{id}")
        ResponseEntity<ExampleResponse> deleteExample(@PathVariable( name = "id") String id) ;
        @PutMapping("{id}")
        ResponseEntity<ExampleResponse> updateExample(@PathVariable( name = "id") String id,
                                                      @RequestParam( "Example") String Example) ;

        @GetMapping
        ResponseEntity<ExampleResponse> findExample(@RequestParam("Example") String Example);

        @PutMapping("/activate/{id}")
        ResponseEntity<ExampleResponse> activate(@PathVariable( name = "id") String id);


        @PutMapping("/deactivate/{id}")
        ResponseEntity<ExampleResponse> deactivate(@PathVariable(name = "id") String id);
}

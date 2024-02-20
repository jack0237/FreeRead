package com.Icekiwi.Freeread.rest.api;

import com.Icekiwi.Freeread.exceptions.AlreadyExistsException;
import com.Icekiwi.Freeread.rest.dto.request.CategorieCreateRequest;
import com.Icekiwi.Freeread.rest.dto.responses.categorie.CategorieResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/categorie", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CategorieApi {
    @PostMapping
    ResponseEntity<CategorieResponse> createCategorie(@Valid @RequestBody CategorieCreateRequest requestDto) throws AlreadyExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<CategorieResponse> deleteCategorie(@PathVariable(name = "id") Long id);

    @GetMapping
    ResponseEntity<CategorieResponse> findCategorie(@RequestParam("offset") int offset, @RequestParam("limit") int limit);

//    @PutMapping("update/{id}")
//    ResponseEntity<CategorieResponse> updateCategorie(@PathVariable (name = "id") String id,
//                                                      @RequestParam String);
}

package com.Icekiwi.Freeread.rest.controllers;

import com.Icekiwi.Freeread.exceptions.AlreadyExistsException;
import com.Icekiwi.Freeread.rest.api.CategorieApi;
import com.Icekiwi.Freeread.rest.dto.request.CategorieCreateRequest;
import com.Icekiwi.Freeread.rest.dto.responses.categorie.CategorieResponse;
import com.Icekiwi.Freeread.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CategorieController implements CategorieApi {
    private final CategorieService categorieService;

    @Override
    public ResponseEntity<CategorieResponse> createCategorie(CategorieCreateRequest request) throws AlreadyExistsException {
        return ResponseEntity.ok(new CategorieResponse(categorieService.save(request.toEntity())));
    }

    @Override
    public ResponseEntity<CategorieResponse> deleteCategorie(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CategorieResponse> findCategorie(int offset, int limit) {
        return null;
    }

//    @Override
//    public ResponseEntity<CategorieResponse> updateCategorie( String catId, String catName, ) {
//        return ResponseEntity.ok(new CategorieResponse(categorieService.update(catId, catName )));
//    }
}

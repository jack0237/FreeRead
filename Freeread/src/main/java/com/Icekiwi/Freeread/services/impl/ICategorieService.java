package com.Icekiwi.Freeread.services.impl;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import com.Icekiwi.Freeread.dao.repositories.CategorieRepository;
import com.Icekiwi.Freeread.exceptions.AlreadyExistsException;
import com.Icekiwi.Freeread.exceptions.NotFoundException;
import com.Icekiwi.Freeread.services.CategorieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ICategorieService implements CategorieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ICategorieService.class);
    private final CategorieRepository categorieRepository;

    @Override
    public CategorieEntity save(CategorieEntity entity) throws AlreadyExistsException{

        Optional<CategorieEntity> existingField = categorieRepository.findByDeletedFalseAndNomCategorie(entity.getNomCategorie());
        if (existingField.isPresent()) {
            LOGGER.error("field already exists");
            throw new AlreadyExistsException("field already exists");
        }
        return categorieRepository.save(entity);

    }

    @Override
    public CategorieEntity delete(String categorieId) {
        LOGGER.info("delete : {}", categorieId);
        CategorieEntity entity = categorieRepository.findByDeletedFalseAndUuid(categorieId).orElseThrow(
                () -> new NotFoundException("this subject with ID cannot be found : " + categorieId));
        entity.setDeleted(true);
        return categorieRepository.save(entity);
    }
}

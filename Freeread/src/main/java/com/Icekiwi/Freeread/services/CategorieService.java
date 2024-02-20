package com.Icekiwi.Freeread.services;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import com.Icekiwi.Freeread.exceptions.AlreadyExistsException;

public interface CategorieService {
    CategorieEntity save (CategorieEntity entity) throws AlreadyExistsException;
    CategorieEntity delete (String categorieId);


}

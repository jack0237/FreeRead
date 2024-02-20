package com.Icekiwi.Freeread.dao.repositories;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {

    Optional<CategorieEntity> findById(Long id);

    Optional<CategorieEntity> findByDeletedFalseAndUuid(String uuid);
    //List<CategorieEntity> findByDescription(String description);
  //  Optional<CategorieEntity> findByNomAndDescription(String nom, String description);
    Optional<CategorieEntity> findByDeletedFalseAndNomCategorie(String nom);
  //  List<CategorieEntity> findByDeletedFalse();

}


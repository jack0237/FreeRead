package com.Icekiwi.Freeread.dao.repositories;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {
    @Override
    Optional<CategorieEntity> findById(Long aLong);
}

package com.Icekiwi.Freeread.dao.repositories;

import com.Icekiwi.Freeread.dao.entities.DonsEntity;
import com.Icekiwi.Freeread.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonsRepository extends JpaRepository<DonsEntity, Long> {
    @Override
    List<DonsEntity> findAll();

    List<DonsEntity> findByUtilisateur(UserEntity user);
}

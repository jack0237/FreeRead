package com.sklfgroup.auxillium.dao.repositories;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, String> {
    Optional<ExerciseEntity> findByDeletedFalseAndUuid(String Uuid );


}

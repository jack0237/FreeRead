package com.sklfgroup.auxillium.dao.repositories;

import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, String> {
    Optional<ExampleEntity> findByDeletedFalseAndUuid(String Uuid );
//    Optional<ExampleEntity> findByDeletedFalseExample(String Uuid );

}

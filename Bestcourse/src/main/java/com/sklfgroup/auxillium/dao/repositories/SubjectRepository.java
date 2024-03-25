package com.sklfgroup.auxillium.dao.repositories;

import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findByDeletedFalseAndNameSubject(String nameSubject);

    Optional<SubjectEntity> findByDeletedFalseAndUuid(String uuid);

}

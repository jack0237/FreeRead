package com.sklfgroup.auxillium.dao.repositories;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    Optional<LessonEntity> findByDeletedFalseAndTitleLesson(String titleLesson);
    Optional<LessonEntity> findByDeletedFalseAndUuid(String uuid);
    Optional<Page<LessonEntity>> findAllByDeletedFalse(Pageable pageable);
}

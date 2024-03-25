package com.sklfgroup.auxillium.dao.repositories;

import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    Optional<ChapterEntity> findByDeletedFalseAndNameChapter(String nameSubject);
    Optional<ChapterEntity> findByDeletedFalseAndUuid(String uuid);
    Optional<ChapterEntity> findByNameChapter(String nameChapter);
}

package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import org.springframework.data.domain.Page;

public interface ChapterService {
    ChapterEntity save (ChapterEntity entity) throws AlreadyExistsException;
    Object delete(String chapterId);
    Object update(String chapterId, String nameChapter);
    Object find(String nameChapter);
    ChapterEntity activate(String chapterId);
    ChapterEntity deactivate(String chapterId);
}

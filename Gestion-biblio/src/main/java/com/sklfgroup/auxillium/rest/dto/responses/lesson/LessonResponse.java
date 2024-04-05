package com.sklfgroup.auxillium.rest.dto.responses.lesson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.rest.dto.responses.MetaResponse;
import com.sklfgroup.auxillium.rest.dto.responses.chapter.ChapterDataResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonResponse {
    MetaResponse meta;
    LessonDataResponse data;
    List<LessonDataResponse> datas;
    public LessonResponse() {
    }

    public LessonResponse(Object entity) {
        this.data = new LessonDataResponse((LessonEntity) entity);
    }

    public LessonResponse(Page<LessonEntity> entities) {
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = new ArrayList<>();
        entities.forEach(entity -> {
            this.datas.add(new LessonDataResponse(entity));
        });
    }
}

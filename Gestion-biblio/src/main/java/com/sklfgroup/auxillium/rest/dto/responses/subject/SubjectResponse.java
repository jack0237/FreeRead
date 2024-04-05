package com.sklfgroup.auxillium.rest.dto.responses.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
import com.sklfgroup.auxillium.rest.dto.responses.MetaResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectResponse {
    MetaResponse meta;
    SubjectDataResponse data;
    List<SubjectDataResponse> datas;
    public SubjectResponse() {
    }

    public SubjectResponse(Object entity) {
        this.data = new SubjectDataResponse((SubjectEntity) entity);
    }

    public SubjectResponse(Page<SubjectEntity> entities) {
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = new ArrayList<>();
        entities.forEach(entity -> {
            this.datas.add(new SubjectDataResponse(entity));
        });
    }
}

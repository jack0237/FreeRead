package com.sklfgroup.auxillium.rest.dto.responses.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.rest.dto.responses.MetaResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseResponse {
    MetaResponse meta;
    ExerciseDataResponse data;
    List<ExerciseDataResponse> datas;
    public ExerciseResponse() {
    }

    public ExerciseResponse(Object entity) {
        this.data = new ExerciseDataResponse((ExerciseEntity) entity);
    }

    public ExerciseResponse(Page<ExerciseEntity> entities) {
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = new ArrayList<>();
        entities.forEach(entity -> {
            this.datas.add(new ExerciseDataResponse(entity));
        });
    }
}

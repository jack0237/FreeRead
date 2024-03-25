package com.sklfgroup.auxillium.rest.dto.responses.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
import com.sklfgroup.auxillium.rest.dto.responses.MetaResponse;
import com.sklfgroup.auxillium.rest.dto.responses.lesson.LessonDataResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExampleResponse {
    MetaResponse meta;
    ExampleDataResponse data;
    List<ExampleDataResponse> datas;
    public ExampleResponse() {
    }

    public ExampleResponse(ExampleEntity entity)  {
        this.data = new ExampleDataResponse(entity);
    }

    public ExampleResponse(Page<ExampleEntity> entities) {
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = new ArrayList<>();
        entities.forEach(entity -> {
            this.datas.add(new ExampleDataResponse(entity));
        });
    }
}

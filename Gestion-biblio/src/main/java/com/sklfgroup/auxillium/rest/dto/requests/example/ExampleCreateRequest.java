package com.sklfgroup.auxillium.rest.dto.requests.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExampleCreateRequest (
        @NotBlank(message = "Required examples ")
        @JsonProperty(value = "exampleContent", required = true)
        String exampleContent,

        @NotBlank(message = "lesson id is required ")
        @JsonProperty(value = "lessonid", required = true)
        String lessonid
) {

        public ExampleEntity toEntity(){
                ExampleEntity entity = new ExampleEntity();
                entity.setExample(this.exampleContent());
                LessonEntity lessonEntity = new LessonEntity();
                lessonEntity.setUuid(lessonid);
                entity.setLesson(lessonEntity);

                return entity;
        }
}

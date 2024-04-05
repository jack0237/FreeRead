package com.sklfgroup.auxillium.rest.dto.requests.exercise;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExerciseCreateRequest(
        @NotBlank(message = "Required exercise content")
        @JsonProperty(value = "exerciseContent", required = true)
        String exerciseContent,

        @NotBlank(message = "lesson id is required ")
        @JsonProperty(value = "lessonid", required = true)
        String lessonid


){
    public ExerciseEntity toEntity(){
        ExerciseEntity entity = new ExerciseEntity();
        entity.setExercise(this.exerciseContent);
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setUuid(lessonid);
        entity.setLessonEntity(lessonEntity);

        return entity;
    }
}

package com.sklfgroup.auxillium.rest.dto.responses.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ExerciseEntity;
import com.sklfgroup.auxillium.rest.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ExerciseDataResponse extends BaseDto {
    @JsonProperty(value = "Exercise ")
    private String example;
    @JsonProperty(value = "example content")
    private String contentExercise;
    @JsonProperty(value = "Creation date")
    private ZonedDateTime createDate;


    public ExerciseDataResponse(ExerciseEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}

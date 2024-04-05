package com.sklfgroup.auxillium.rest.dto.responses.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;
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
public class LessonDataResponse extends BaseDto {
    @JsonProperty(value = "lesson title")
    private String titleLesson;
    @JsonProperty(value = "lesson content")
    private String contentLesson;
    @JsonProperty(value = "Creation date")
    private ZonedDateTime createDate;


    public LessonDataResponse(LessonEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}

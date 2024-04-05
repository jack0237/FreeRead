package com.sklfgroup.auxillium.rest.dto.responses.chapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.rest.dto.BaseDto;
import com.sklfgroup.auxillium.rest.dto.responses.subject.SubjectDataResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ChapterDataResponse extends BaseDto {
    @JsonProperty(value = "Chapter Name")
    private String nameSubject;
    @JsonProperty(value = "Creation date")
    private ZonedDateTime createDate;
    @JsonProperty(value = "subject")
    private SubjectDataResponse subject;


    public ChapterDataResponse(ChapterEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.subject = new SubjectDataResponse(entity.getSubject());

    }
}

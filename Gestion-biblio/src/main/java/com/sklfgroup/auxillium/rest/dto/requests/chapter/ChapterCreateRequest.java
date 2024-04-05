package com.sklfgroup.auxillium.rest.dto.requests.chapter;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ChapterCreateRequest(
        @NotBlank(message = "Required Chapter Name")
        @JsonProperty(value = "nameChapter", required = true)
        String nameChapter,

        @NotBlank(message = "Subject id is required ")
        @JsonProperty(value = "subjectid", required = true)
        String subjectid

//        @NotBlank(message = "Creation date " )
//        @JsonProperty(value = "createdAt ", required = true )
//        ZonedDateTime createdAt

){
    public ChapterEntity toEntity(){
        ChapterEntity entity = new ChapterEntity();
        entity.setNameChapter(this.nameChapter().toUpperCase(Locale.ROOT));
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setUuid(subjectid);
        entity.setSubject(subjectEntity);

        return entity;
    }
}

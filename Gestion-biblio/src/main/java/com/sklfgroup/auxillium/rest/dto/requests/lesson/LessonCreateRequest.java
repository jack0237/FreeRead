package com.sklfgroup.auxillium.rest.dto.requests.lesson;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ChapterEntity;
import com.sklfgroup.auxillium.dao.entities.LessonEntity;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record LessonCreateRequest(
        @NotBlank(message = "Required Lesson Name")
        @JsonProperty(value = "titleLesson", required = true)
        String titleLesson,
        @NotBlank(message = "Lesson content is required")
        @JsonProperty(value = "contentLesson", required = true)
        String contentLesson,
        @NotBlank(message = "Chapter id is required ")
        @JsonProperty(value = "chapterId", required = true)
        String chapterId
){
    public LessonEntity toEntity(){
        LessonEntity entity = new LessonEntity();
        entity.setTitleLesson(this.titleLesson);
        entity.setContentLesson(this.contentLesson);
        ChapterEntity chapterEntity = new ChapterEntity();
        chapterEntity.setUuid(chapterId);
        entity.setChapter(chapterEntity);
        return entity;
    }
}

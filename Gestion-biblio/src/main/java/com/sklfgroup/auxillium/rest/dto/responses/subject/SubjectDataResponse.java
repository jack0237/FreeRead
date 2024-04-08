package com.sklfgroup.auxillium.rest.dto.responses.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;
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
public class SubjectDataResponse extends BaseDto {
    @JsonProperty(value = "Subject Name")
    private String nameSubject;
    @JsonProperty(value = "Creation date")
    private ZonedDateTime createDate;


    public SubjectDataResponse (SubjectEntity entity){
        this.uuid = entity.getUuid();

        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}

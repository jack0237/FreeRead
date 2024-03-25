package com.sklfgroup.auxillium.rest.dto.responses.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.ExampleEntity;
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
public class ExampleDataResponse extends BaseDto {
    @JsonProperty(value = "Examples ")
    private String example;
    @JsonProperty(value = "example content")
    private String contentExample;
    @JsonProperty(value = "Creation date")
    private ZonedDateTime createDate;

    public ExampleDataResponse(ExampleEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

}

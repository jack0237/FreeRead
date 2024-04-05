package com.sklfgroup.auxillium.rest.dto.requests.subject;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.SubjectEntity;

import javax.validation.constraints.NotBlank;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record SubjectCreateRequest (
       @NotBlank(message = "Required subject Name")
       @JsonProperty(value = "nameSubject", required = true)
        String nameSubject


){
    public SubjectEntity toEntity(){
        SubjectEntity entity = new SubjectEntity();
        entity.setNameSubject(this.nameSubject);
        return entity;
    }
}

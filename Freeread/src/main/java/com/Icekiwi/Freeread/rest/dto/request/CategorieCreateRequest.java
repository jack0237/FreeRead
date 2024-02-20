package com.Icekiwi.Freeread.rest.dto.request;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public record CategorieCreateRequest (
    @NotBlank(message =  " Categorie name is required")
    @JsonProperty(value = "NomDeLaCategorie", required = true)
    String nomCategorie,

    @NotBlank(message = "description is required")
    @JsonProperty(value = "Description")
    String Description,

    @NotBlank(message = "not null")
    @JsonProperty(value = "ouvrage")
    String Ouvrage
){


    public CategorieEntity toEntity(){
        CategorieEntity entity = new CategorieEntity();
        entity.setNomCategorie(this.nomCategorie().toUpperCase(Locale.ROOT));
        return entity;
    }
}


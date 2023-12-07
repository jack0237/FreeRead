package com.Icekiwi.Freeread.rest.dto.responses.categorie;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import com.Icekiwi.Freeread.dao.entities.OuvrageEntity;
import com.Icekiwi.Freeread.rest.dto.BaseDto;
import com.Icekiwi.Freeread.rest.dto.responses.ouvrage.OuvrageDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class CategorieDataResponse extends BaseDto {

    @JsonProperty(value = "Ouvrage")
    private OuvrageEntity ouvrages;
    @JsonProperty(value = "nomCategorie")
    private String nomCategorie;
    @JsonProperty(value = "Description")
    private String Description;

    public CategorieDataResponse(CategorieEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.nomCategorie = entity.getNomCategorie();
//        this.ouvrages = new OuvrageDataResponse(entity.getOuvrages());
    }
}

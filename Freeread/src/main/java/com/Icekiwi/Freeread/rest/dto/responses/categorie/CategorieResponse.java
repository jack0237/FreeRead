package com.Icekiwi.Freeread.rest.dto.responses.categorie;

import com.Icekiwi.Freeread.dao.entities.CategorieEntity;
import com.Icekiwi.Freeread.rest.dto.responses.MetaResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieResponse {
    MetaResponse meta;
    CategorieDataResponse data;
    List<CategorieDataResponse> datas;

    public CategorieResponse (CategorieEntity entity) {this.data = new CategorieDataResponse(entity);}

    public CategorieResponse(Page<CategorieEntity> entities){
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = new ArrayList<>();
        entities.map(entity -> {
           this.datas.add(new CategorieDataResponse(entity));
           System.out.print(datas);
           return datas;
        });
    }
}















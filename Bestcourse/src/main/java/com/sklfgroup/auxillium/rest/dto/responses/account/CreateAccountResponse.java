package com.sklfgroup.auxillium.rest.dto.responses.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sklfgroup.auxillium.rest.dto.responses.MetaResponse;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAccountResponse {
    MetaResponse meta;
    CreateAccountDataResponse data;
    List<CreateAccountDataResponse> datas;

    public CreateAccountResponse(CreateAccountDataResponse data){
        this.data = data;
    }

    public CreateAccountResponse(Page<AccountEntity> entities){
        this.meta = new MetaResponse(entities.hasPrevious(), entities.hasNext(), entities.getTotalPages());
        this.datas = map(entities);
    }

    private CreateAccountDataResponse map(AccountEntity entity){
        return new CreateAccountDataResponse(entity);
    }

    private List<CreateAccountDataResponse> map(Page<AccountEntity> entities){
        return entities.stream().map(this::map).collect(Collectors.toList());
    }
}

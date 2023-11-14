package com.sklfgroup.auxillium.rest.dto.responses.account;

import com.sklfgroup.auxillium.rest.dto.responses.BaseDto;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateAccountDataResponse extends BaseDto {
    String firstname;
    String lastname;
    String email;

    public CreateAccountDataResponse(AccountEntity entity){
        this.uuid = entity.getUuid();
        this.deleted = entity.isDeleted();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.email = entity.getEmail();
    }
}

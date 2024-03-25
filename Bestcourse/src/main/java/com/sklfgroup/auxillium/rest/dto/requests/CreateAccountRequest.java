package com.sklfgroup.auxillium.rest.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import com.sklfgroup.auxillium.dao.entities.account.Gender;
import com.sklfgroup.auxillium.dao.entities.account.HighestSchoolLevel;
import com.sklfgroup.auxillium.helpers.StringHelper;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateAccountRequest(
        @NotBlank(message = "firstname is required")
        @JsonProperty(value = "firstname", required = true)
        String firstname,

        @NotBlank(message = "lastname is required")
        @JsonProperty(value = "lastname", required = true)
        String lastname,

        @NotBlank(message = "address is required")
        @JsonProperty(value = "address", required = true)
        String address,

        @Email
        @NotBlank(message = "email is required")
        @JsonProperty(value = "email", required = true)
        String email,

        @NotBlank(message = "phoneNumber is required")
        @JsonProperty(value = "phoneNumber", required = true)
        String phoneNumber,

        @Min(value = 0, message = "countryCode must be greater than 0")
        @JsonProperty(value = "countryCode", required = true)
        long countryCode,

        @NotBlank(message = "presentSituation is required")
        @JsonProperty(value = "presentSituation", required = true)
        String presentSituation,

        @NotBlank(message = "heardAboutAuxillium is required")
        @JsonProperty(value = "heardAboutAuxillium", required = true)
        String heardAboutAuxillium,

        @NotNull(message = "highestSchoolLevel is required")
        @JsonProperty(value = "highestSchoolLevel", required = true)
        HighestSchoolLevel highestSchoolLevel,

        @NotNull(message = "gender is required")
        @JsonProperty(value = "gender", required = true)
        Gender gender,

        @NotBlank(message = "password is required")
        @JsonProperty(value = "password", required = true)
        String password

        ) {

    public AccountEntity toEntity(){
        AccountEntity entity = new AccountEntity();
        entity.setFirstname(StringHelper.capitalizeFirstCharacter(firstname));
        entity.setLastname(lastname.toUpperCase());
        entity.setAddress(StringHelper.capitalizeFirstCharacter(address));
        entity.setPhoneNumber(phoneNumber);
        entity.setCountryCode(countryCode);
        entity.setEmail(email.toLowerCase());
        entity.setGender(gender);
        entity.setHeardAboutAuxillium(heardAboutAuxillium);
        entity.setHighestSchoolLevel(highestSchoolLevel);
        entity.setPresentSituation(presentSituation);
        entity.setPassword(password);
        return entity;
    }
}

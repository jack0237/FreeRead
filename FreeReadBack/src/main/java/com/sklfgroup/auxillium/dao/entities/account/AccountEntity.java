package com.sklfgroup.auxillium.dao.entities.account;

import com.sklfgroup.auxillium.dao.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country_code")
    private long countryCode;

    @Column(name = "present_situation")
    private String presentSituation;

    @Column(name = "heardAbout_auxillium")
    private String heardAboutAuxillium;

    @Column(name = "password", nullable = false)
    private String password;

    @JoinColumn(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JoinColumn(name="highest_school_Level")
    @Enumerated(EnumType.STRING)
    private HighestSchoolLevel highestSchoolLevel;


}

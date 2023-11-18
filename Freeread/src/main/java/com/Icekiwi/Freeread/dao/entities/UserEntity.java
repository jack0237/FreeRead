package com.Icekiwi.Freeread.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class UserEntity extends BaseEntity{
    @Column(name = "Nom d'utilisateur")
    private String UserName;
    @Column(name = "e-mail")
    private String Email;
    @Column(name = "e-mail")
    private String MotdePasse;

}

package com.Icekiwi.Freeread.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class UserEntity extends BaseEntity{
    @Column(name = "Nom_d'utilisateur")
    private String UserName;
    @Column(name = "e-mail")
    private String Email;
    @Column(name = "Motdepasse")
    private String MotdePasse;

    @ManyToOne
    private DonsEntity Dons;

    @ManyToOne
    @JoinColumn(name = "Pret")
    private PretEntity pret;

}

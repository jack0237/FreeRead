package com.Icekiwi.Freeread.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Pret")
public class PretEntity extends BaseEntity {

    @Column(name = "Motif de pret")
    private String MotifPret;
    @Column(name = "Date de remise")
    private Date DateRemise;


    @OneToMany
    @JoinColumn(name = "Ouvrage")
    private OuvrageEntity Ouvrage;

    @JoinColumn(name = "Utilisateur")
    private UserEntity Utilisateur;

}

package com.Icekiwi.Freeread.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
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


    @ManyToOne
    private OuvrageEntity Ouvrage;

    @ManyToOne
    private UserEntity Utilisateur;

}

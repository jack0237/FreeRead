package com.Icekiwi.Freeread.dao.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;

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

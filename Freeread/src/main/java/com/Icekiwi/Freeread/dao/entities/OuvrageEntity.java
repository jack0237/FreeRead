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
@Table(name = "Ouvrage")
public class OuvrageEntity extends BaseEntity{
    @Column(name = "nom")
    private String nomOuvrage;
    @Column(name = "Nature d'ouvrage")
    private String Nature;
    @Column(name = "Auteur")
    private String Auteur;
    @Column(name = "Quantite")
    private Integer Quantite;
    @Column(name = "Description")
    private String Description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategorieEntity Categorie;


}

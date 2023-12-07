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
@Table(name = "category")
public class CategorieEntity extends BaseEntity{
    @Column(name = "nomCategorie")
    private String nomCategorie;
    @Column(name = "Description")
    private String Description;

    @ManyToOne
    @JoinColumn(name = "Ouvrage")
    private OuvrageEntity ouvrages;

}

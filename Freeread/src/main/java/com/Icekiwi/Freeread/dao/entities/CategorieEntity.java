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
public class CategorieEntity extends BaseEntity{
    @Column(name = "nom")
    private String nomCategorie;
    @Column(name = "Description")
    private String Description;

}

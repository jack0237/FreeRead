package com.Icekiwi.Freeread.dao.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "dons")
public class DonsEntity extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "Utilisateur")
    private UserEntity Utilisateur;
}

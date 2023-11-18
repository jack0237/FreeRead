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

public class DonsEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "Ouvrage")
    private OuvrageEntity Ouvrage;
}

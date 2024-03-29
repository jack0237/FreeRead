package com.isj.gestiondenote.ClientWeb.Model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table niveau dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */


@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Niveau extends Securite implements Serializable {

    public Niveau() {

    }

    private int numero;

  /*  @OneToMany(mappedBy = "niveau",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Classe> classes = new ArrayList<>();

    @OneToMany(mappedBy = "niveau",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <UE> ues = new ArrayList<>();*/

    public Niveau(String libelle, String description, int numero) {
        super(libelle, description);
        this.numero = numero;
    }

    public Niveau(int numero) {
        this.numero = numero;
    }

    @Override
    public String getLibelle(){
        return super.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),getLibelle(), numero);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}

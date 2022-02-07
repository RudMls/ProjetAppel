package com.example.projetappel.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "enseignant_id")
public class Enseignant extends Utilisateur implements Serializable {

    public Enseignant() {}



    public Enseignant(String prenom, String nom, String email, String password) {
        super(prenom, nom, email, password);
    }

}

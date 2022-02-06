package com.example.projetappel.model;

import com.example.projetappel.enumtype.TypeEtudiant;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Etudiant extends Utilisateur implements Serializable {


    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;

    @ManyToOne
    private Groupe groupe;

    public Etudiant() {}

    public Etudiant(int id, String prenom, String nom, String email, String password) {
        super(id, prenom, nom, email, password);
    }

    public Etudiant(int id, String prenom, String nom, String email, String password, String imageUrl, TypeEtudiant typeEtudiant, Groupe groupe) {
        super(id, prenom, nom, email, password);
        this.imageUrl = imageUrl;
        this.typeEtudiant = typeEtudiant;
        this.groupe = groupe;
    }

    public Etudiant(String prenom, String nom, String email, String password, TypeEtudiant typeEtudiant) {
        super(prenom, nom, email, password);
        this.typeEtudiant = typeEtudiant;
    }

}

package com.example.projetappel.model;

import com.example.projetappel.enumtype.TypeEtudiant;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn
public class Etudiant extends Utilisateur implements Serializable {


    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;

    @OneToMany(mappedBy = "etudiant")
    private Set<Presence> presences = new HashSet<>();

    @OneToMany(mappedBy = "etudiant")
    private Set<Absence> absences = new HashSet<>();

    @OneToMany(mappedBy = "etudiant")
    private Set<Appartenir> appartenirs = new HashSet<>();

    public Etudiant() {}

    public Etudiant(int id, String prenom, String nom, String email, String password) {
        super(id, prenom, nom, email, password);
    }

    public Etudiant(int id, String prenom, String nom, String email, String password, String imageUrl, TypeEtudiant typeEtudiant) {
        super(id, prenom, nom, email, password);
        this.imageUrl = imageUrl;
        this.typeEtudiant = typeEtudiant;
    }

    public Etudiant(String prenom, String nom, String email, String password, TypeEtudiant typeEtudiant) {
        super(prenom, nom, email, password);
        this.typeEtudiant = typeEtudiant;
    }




    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TypeEtudiant getTypeEtudiant() {
        return typeEtudiant;
    }

    public void setTypeEtudiant(TypeEtudiant typeEtudiant) {
        this.typeEtudiant = typeEtudiant;
    }

}

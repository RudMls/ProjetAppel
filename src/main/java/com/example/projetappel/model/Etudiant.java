package com.example.projetappel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String prenom;

    private String nom;

    public Etudiant() {}

    public Etudiant(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Etudiant(int id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

}

package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    public Enseignant() {}

    public Enseignant(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Enseignant(int id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

}
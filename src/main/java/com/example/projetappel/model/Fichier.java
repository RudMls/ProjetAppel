package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Fichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String driveId;

    @Column(nullable = false)
    private String nom;

    private String type;

    public Fichier() {}

    public Fichier(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }
}
